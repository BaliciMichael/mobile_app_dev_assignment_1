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

    override fun create(placemark: Player) {
        players.add(placemark)
        logAll()
    }

    fun update(player: Player) {
        var foundPlayer: Player? = players.find { p -> p.id == player.id }
        if (foundPlayer != null) {
            foundPlayer.name = player.name
            foundPlayer.last = player.last
            foundPlayer.nationality = player.nationality
            foundPlayer.age = player.age
            foundPlayer.club = player.club
            foundPlayer.position = player.position
            foundPlayer.numOfMvp = player.numOfMvp
            foundPlayer.mvp = player.mvp
            logAll()
        }
    }
    fun logAll() {
        players.forEach{ println("${it}") }
    }
}
