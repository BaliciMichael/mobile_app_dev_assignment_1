package ie.setu.assignment1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ie.setu.assignment1.databinding.CardPlayerBinding
import ie.setu.assignment1.models.Player
import ie.setu.assignment1.adapters.PlayerListener

class PlayerAdapter constructor(private var players: List<Player>,private val listener: PlayerListener) :
    RecyclerView.Adapter<PlayerAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardPlayerBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val player = players[holder.adapterPosition]
        holder.bind(player,listener)

    }

    override fun getItemCount(): Int = players.size


    class MainHolder(private val binding : CardPlayerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(player: Player, listener: PlayerListener) {
            binding.playerName.text = player.name.capitalize() + " " + player.last.capitalize()
            binding.playerAge.text = "Age: "+ player.age.toString()
            binding.playerNationality.text = "Nationality: "+player.nationality.capitalize()
            binding.playerClub.text = "Team: "+player.club.capitalize()
            binding.playerPosition.text ="Position: "+ player.position.capitalize()
            binding.numOfMVP.text ="Number of MVP's: " + player.numOfMvp
            binding.updateButton.setOnClickListener { listener.onUpdateClick(player)}
            binding.deleteButton.setOnClickListener{listener.onDeleteClick(player)}
            Picasso.get().load(player.imageUri).into(binding.playerImage)
        }
    }
}