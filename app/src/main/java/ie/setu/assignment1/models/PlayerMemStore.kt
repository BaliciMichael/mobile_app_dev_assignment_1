package ie.setu.assignment1.models

import android.util.Log.i


class PlayerMemStore : PlayerStore {

    val players = ArrayList<Player>()

    override fun findAll(): List<Player> {
        return players
    }
    fun findById(id: Long):Player? {
        for (player in players) {
            if (player.id == id) {
                return player
            }
        }
        return null
    }

    override fun create(player: Player) {
        players.add(player)
        logAll()
    }

    fun update(playerId: Long,playerName: String,lastname: String,playerAge: Int, nationality: String, mvp: Boolean, numOfMvp: Int, club: String, position: String) {
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
            println("Player with the id: ${playerId} has been successfully updated!")
            logAll()
        }
    }
    fun logAll() {
        players.forEach{ println("${it}") }
    }
}
