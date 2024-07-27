package com.kapil.NgoEventManager.controller;

import com.kapil.NgoEventManager.modal.PlanType;
import com.kapil.NgoEventManager.modal.User;
import com.kapil.NgoEventManager.response.PaymentLinkResponse;
import com.kapil.NgoEventManager.service.UserService;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Value("${razorpay.api.key}")
    private String apiKey;

    @Value("${razorpay.api.secret}")
    private String apiSecret;

    @Autowired
    private UserService userService;

    @PostMapping("/{plantype}")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @PathVariable PlanType plantype,
            @RequestHeader ("Authorization") String jwt
            )throws Exception{
        User user=userService.findUserProfileByJwt(jwt);
        int amount=799*100;
        if(plantype.equals(plantype.ANNUALLY)){
            amount=amount*12;
            amount=(int)(amount*0.7);
        }

            RazorpayClient razorpay= new RazorpayClient(apiKey,apiSecret);
            JSONObject paymentLinkRequest =new JSONObject();
            paymentLinkRequest.put("amount", amount);
            paymentLinkRequest.put("current","INR");

            JSONObject customer=new JSONObject();
            customer.put("name", user.getFullName());
            customer.put("email", user.getEmail());
            paymentLinkRequest.put("customer", customer);

            JSONObject notify=new JSONObject();
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);

            // give url from your frontend 5:35:49
            paymentLinkRequest.put("callback_url", "http:5173/upgrade_plan/success"+plantype);

            PaymentLink payment=razorpay.paymentLink.create(paymentLinkRequest);

            String paymentLinkId= payment.get("id");
            String paymentLinkUrl= payment.get("short_url");

            PaymentLinkResponse res=new PaymentLinkResponse();
            res.setPayment_link_id(paymentLinkId);
            res.setPayment_link_url(paymentLinkUrl);

            return new ResponseEntity<>(res, HttpStatus.CREATED);

    }

}
