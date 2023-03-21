package ie.setu.assignment1.models


import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import ie.setu.assignment1.helpers.*
import java.lang.reflect.Type
import java.util.*


const val JSON_FILE = "players.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<Player>>() {}.type



class PlayerJSONStore(private val context: Context) : PlayerStore {

    var players = mutableListOf<Player>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<Player> {
        logAll()
        return players
    }

    override fun create(player: Player) {
        players.add(player)
        logAll()
        serialize()
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
            println("Player with the id: ${playerId} has been successfully updated!")
            logAll()
            serialize()
        }
    }

    override fun removePlayer(id: Long) {
        players.removeIf { it.id == id }
    }
    override fun findById(id: Long):Player? {
        for (player in players) {
            if (player.id == id) {
                return player
            }
        }
        return null
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(players, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        players = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        players.forEach {println("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}
