import SubscriptionCard from "./SubscriptionCard";


const paidPlan = [
	"Everything from Previous Plan+ ",
	"Access to All Events",
];

const annualPlan = [
	"Everything from Previous Plan+ ",
    "Some Random Things idk"
];

const freePlan = [
	"Be a Part of our NGO",
	"Access to Limited Events",
];
 

const Subscription = () => {
  return (
    <div className="p-10">
        <h1 className="text-5xl font-semibold py-5 pb-17 text-center "> Pricing </h1>
        <div className="flex flex-col lg:flex-row justify-center items-center gap-9">
            <SubscriptionCard 
            data={{
                planName:"Free",
                fetures:freePlan, 
                planType:"FREE",
                price:0,
                buttonName:true?"Current Plan":"Get Started",
                }}  
            />
            <SubscriptionCard
                        data={{
                            planName:"Monthly Paid Plan",
                            fetures:paidPlan, 
                            planType:"MONTHLY",
                            price:699,
                            buttonName:true?"Current Plan":"Get Started",
                            }}  
            />
            <SubscriptionCard
                        data={{
                            planName:"Annual Paid Plan",
                            fetures:annualPlan, 
                            planType:"ANUALLY",
                            price:6799,
                            buttonName:true?"Current Plan":"Get Started",
                            }}  
            />
        </div>
    </div>
  )
}

export default Subscription