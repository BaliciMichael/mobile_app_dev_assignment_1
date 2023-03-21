package ie.setu.assignment1.models

import android.content.Context
import android.net.Uri
import android.util.Log.i
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import ie.setu.assignment1.helpers.exists
import ie.setu.assignment1.helpers.read
import ie.setu.assignment1.helpers.write
import java.lang.reflect.Type
import java.util.*

interface PlayerStore {
    fun findAll(): List<Player>
    fun create(player: Player)
    fun update(playerId: Long,playerName: String,lastname: String,playerAge: Int, nationality: String, mvp: Boolean, numOfMvp: Int, club: String, position: String, playerimage: Uri)
    fun removePlayer(id:Long)
    fun findById(id: Long):Player?
}
class PlayerMemStore : PlayerStore {

    val players = ArrayList<Player>()


    override fun findAll(): List<Player> {
        return players
    }
    override fun findById(id: Long):Player? {
        for (player in players) {
            if (player.id == id) {
                return player
            }
        }
        return null
    }
    override fun removePlayer(id: Long){
        //checks if the id is equal to the id in the arraylist if it is it will remove it
        players.removeIf { it.id == id }

    }

    override fun create(player: Player) {
        players.add(player)
        logAll()
    }

    override fun update(playerId: Long,playerName: String,lastname: String,playerAge: Int, nationality: String, mvp: Boolean, numOfMvp: Int, club: String, position: String, playerimage: Uri) {
        var foundPlayer: Player? = players.find { p -> p.id == playerId }
        if (foundPlayer != null) {
            foundPlayer.name = playerName
            foundPlayer.last = lastname
            foundPlayer.nationality = nationality
            foundPlayer.age = playerAge
            foundPlayer.club = club
            foundPlayer.position = position
            foundPlayer.numOfMvp = numOfMvp
            foundPlayer.mvp = mvp
            foundPlayer.imageUri = playerimage
            println("Player with the id: ${playerId} has been successfully updated!")
            logAll()
        }
    }
    fun logAll() {
        players.forEach{ println("${it}") }
    }

}
