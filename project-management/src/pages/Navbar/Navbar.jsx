import { Button } from '@/components/ui/button'
import { Dialog, DialogContent, DialogHeader, DialogTrigger } from '@/components/ui/dialog'
import CreateProjectForm from '../Project/CreateProjectForm'
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger } from '@/components/ui/dropdown-menu'
import { PersonIcon } from '@radix-ui/react-icons'
import { useNavigate } from 'react-router-dom'

const Navbar = () => {
    const navigate=useNavigate();
  return (
    <div className='border-b py-4 px-5 flex items-center justify-between'>
        <div className='flex items-center gap-3'>
            <p onClick={()=>navigate("/")} className='cursor-pointer'> NGO Management</p>
            
            <Dialog>
                <DialogTrigger>
                    <Button variant='ghost'> New Event</Button>
                </DialogTrigger>

                <DialogContent>
                <DialogHeader> Create New Event </DialogHeader>
                    <CreateProjectForm/>
                </DialogContent>
            </Dialog>

            <Button onClick={()=>navigate("/upgrade_plan")} variant='ghost'>Upgrade</Button>
        </div>

        <div className='flex gap-3 items-center'>
        <DropdownMenu>
            <DropdownMenuTrigger>
                <Button variant='outline' size='icon' className='rounded-full border-2 border-gray-400'>
                    <PersonIcon/>
                </Button>
            </DropdownMenuTrigger>

            <DropdownMenuContent>
                <DropdownMenuItem>Logout</DropdownMenuItem>
            </DropdownMenuContent>
        </DropdownMenu>
        <p>User</p>
        </div>
    </div>
  )
}

export default Navbar