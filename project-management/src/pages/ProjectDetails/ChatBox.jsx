import { Avatar, AvatarFallback } from '@/components/ui/avatar'
import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { ScrollArea } from '@/components/ui/scroll-area'
import { PaperPlaneIcon } from '@radix-ui/react-icons'
import React, { useState } from 'react'

const ChatBox = () => {

    const [message,setMessage] = useState("");
    const handleSendMessage=()=>{
        console.log("message", message)
    }
    const handleMessageChange=(e)=>{
        setMessage(e.target.value);
    }


  return (
    <div className='sticky'>
        <div className='border rounded-lg'>
            <h1 className='border-b p-5'> ChatBox</h1>
            <ScrollArea className="h-[32rem] w-full p-5 flex gap-3 flex-col">

            {[1,1,1,1].map((item, index) => 
            index%2==0?            <div className='flex gap-2 mb-2 justify-start' key={item}>
            <Avatar>
                <AvatarFallback>
                    H
                </AvatarFallback>

            </Avatar>
                <div className='space-y-2 py-2 px-5 border rounded-ss-2xl rounded-e-xl'>
                    <p> Harsh </p>
                    <p className='text-gray-300'> Test Message </p>

                </div>
        </div>:

            <div className='flex gap-2 mb-2 justify-end' key={item}>
                    <div className='space-y-2 py-2 px-5 border rounded-ss-2xl rounded-s-xl'>
                        <p> Harsh </p>
                        <p className='text-gray-300'> Test Message </p>
                    </div>

                    <Avatar>
                    <AvatarFallback>
                        H
                    </AvatarFallback>
                </Avatar>
            </div>
            )}

            </ScrollArea>

            <div className='relative p-0'>
                <Input 
                placeHolder = "type something..."
                className = "py-7 border-t outline-none focus:outline-none focus:ring-0 rounded-none border-b-0 border-x-0"
                value={message} onChange={handleMessageChange}  />
                <Button onClick={handleSendMessage} size="icon" variant="ghost"
                className="absolute right-2 top-3 rounded-full">
                    <PaperPlaneIcon/>
                </Button>
            </div>
        </div>
    </div>
  )
}

export default ChatBox