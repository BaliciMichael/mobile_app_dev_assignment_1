package ie.setu.assignment1.main

import android.app.Application
import ie.setu.assignment1.models.PlayerJSONStore
import ie.setu.assignment1.models.PlayerStore
import ie.setu.assignment1.models.PlayerMemStore

class MainApp : Application() {

    lateinit var players: PlayerStore

    override fun onCreate() {
        super.onCreate()
        players = PlayerJSONStore(applicationContext)
    }
}