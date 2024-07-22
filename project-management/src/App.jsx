import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { Button } from './components/ui/button'
import Home from './pages/Home/Home'
import Navbar from './pages/Navbar/Navbar'
import { Route, Routes } from 'react-router-dom'
import { ProjectDetails } from './pages/ProjectDetails/ProjectDetails'
import IssueDetails from './pages/IssueDetails/IssueDetails'
import Subscription from './pages/Subscription/Subscription'
import Auth from './pages/Auth/Auth'


function App() {
  const [count, setCount] = useState(0)

  return (
    <>  
    {
      true?<div>
      <Navbar/>
      <Routes>
        <Route path="/" element={<Home/>} />
        <Route path="/project/:id" element={<ProjectDetails />} />
        <Route path="/project/:projectId/issue/:issueId" element={<IssueDetails />} />
        <Route path="/upgrade_plan" element={<Subscription/>} />
      </Routes>
      </div>:<Auth/>
    }
    </>
  )
}

export default App
