package ie.setu.assignment1.main

import android.app.Application
import android.net.Uri
import ie.setu.assignment1.models.Player
import ie.setu.assignment1.models.PlayerMemStore

class MainApp : Application() {

    //val players.txt = ArrayList<Player>()
    val players = PlayerMemStore()
    override fun onCreate() {
        super.onCreate()
        println("Activity started")
       players.create(Player(1,"Nikola","Jokic",24,"Serbia",true,2,"Denver Nuggets","Center",))
        players.create(Player(2,"Luka","Doncic",24,"Slovenia",true,1,"Dallas Mavericks","Point Guard", Uri.parse("https://cdn.nba.com/headshots/nba/latest/1040x760/1629029.png")))
        players.create(Player(3,"James","Harden",27,"USA",false,0,"Utah Jazz","Shooting Guard"))

    }
}
