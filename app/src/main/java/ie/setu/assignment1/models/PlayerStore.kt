package ie.setu.assignment1.models

interface PlayerStore {
    fun findAll(): List<Player>
    fun create(player: Player)

    fun removePlayer(id:Long)
}