import { Avatar, AvatarFallback } from '@/components/ui/avatar'
import { Badge } from '@/components/ui/badge'
import { Button } from '@/components/ui/button'
import { Dialog, DialogContent, DialogHeader, DialogTrigger } from '@/components/ui/dialog'
import { PlusIcon } from '@radix-ui/react-icons'
import React from 'react'
import { InviteUserForm } from './InviteUserForm'
import { ScrollArea } from '@/components/ui/scroll-area'
import IssueList from './IssueList'
import ChatBox from './ChatBox'

export const ProjectDetails = () => {
  const handleProjectInvitation = () => {
    // Implementation here
  }

  return (
    <div className='mt-5 lg:px-10'>
      <div className='lg:flex gap-5 justify-between pb-4'>
        <ScrollArea className='h-screen lg:w-[69%] pr-2'>
          <div className='text-gray-400 pb-10 w-full'>
            <h1 className="text-lg font-semibold pb-5">
              Create Ecommerce Project
            </h1>
            <div className='space-y-5 pb-10 text-sm'>
              <p className='w-full md:max-lg lg:max-w-xl'>
                Lorem ipsum dolor, sit amet consectetur adipisicing elit. Quia, deserunt.
              </p>

              <div className='flex'>
                <p className='w-36'>Project Lead:</p>
                <p>Harsh</p>
              </div>

              <div className='flex'>
                <p className='w-36'>Members</p>
                <div className='flex items-center gap-2'>
                  {[1, 2, 3, 4].map((item) => (
                    <Avatar className="cursor-pointer" key={item}>
                      <AvatarFallback aria-label={`Team member ${item}`}> H </AvatarFallback>
                    </Avatar>
                  ))}
                </div>

                <div>
                  <Dialog>
                    <DialogTrigger asChild>
                      <Button size="sm" variant="outline" onClick={handleProjectInvitation} className="ml-2">
                        <span>invite</span>
                        <PlusIcon className="w-3 h-3" />
                      </Button>
                    </DialogTrigger>

                    <DialogContent>
                      <DialogHeader>Invite User</DialogHeader>
                      <InviteUserForm />
                    </DialogContent>
                  </Dialog>
                </div>
              </div>

              <div className='flex'>
                <p className='w-36'>Category:</p>
                <p>Fullstack</p>
              </div>

              <div className='flex'>
                <p className='w-36'>Project Lead:</p>
                <Badge>Harsh</Badge>
              </div>
            </div>

            <section>
              <p className='py-5 border-b text-lg tracking-wider'>Tasks</p>
              <div className='lg:flex md:flex gap-3 justify-between py-5'> 
                <IssueList status="pending" title="Todo List" key="todo" />
                <IssueList status="in_progress" title="In Progress" key="inprogress" />
                <IssueList status="done" title="Done" key="done" />
              </div>
            </section>
          </div>
        </ScrollArea>

        <div className='lg:w-[30%] rounded-md sticky right-5 top-10'> 
          <ChatBox />
        </div>
      </div>
    </div>
  )
}
