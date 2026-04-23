import React from 'react'
import './AppDownland.css'
import { assets } from '../../assets/assets'
const AppDownland = () => {
  return (
    <div className="app-downland" id ="app-downland">
        
        <p>For Better Experience Downland <br/> Tomato App</p>
    <div className="app-downland-platforms">
      <img src={assets.play_store} alt="" />
      <img src={assets.app_store} alt="" />
    </div>
    </div>
  )
}

export default AppDownland