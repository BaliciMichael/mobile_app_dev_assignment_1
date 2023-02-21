package ie.setu.assignment1.main

import android.app.Application
import ie.setu.assignment1.models.Player
import ie.setu.assignment1.models.PlayerMemStore

class MainApp : Application() {

    //val players = ArrayList<Player>()
    val players = PlayerMemStore()
    override fun onCreate() {
        super.onCreate()
        println("Activity started")



    }
}
