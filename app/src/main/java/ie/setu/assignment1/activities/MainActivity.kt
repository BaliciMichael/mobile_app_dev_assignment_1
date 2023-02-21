package ie.setu.assignment1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ie.setu.assignment1.databinding.ActivityMainBinding
import ie.setu.assignment1.activities.AddPlayer
import ie.setu.assignment1.databinding.CardPlayerBinding
import ie.setu.assignment1.main.MainApp
import ie.setu.assignment1.models.Player

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var app: MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MainApp

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = PlayerAdapter(app.players)

        binding.addPlayerButton.setOnClickListener { val intent=Intent(this, AddPlayer::class.java)
                startActivity(intent)
        }
    }
    class PlayerAdapter constructor(private var players: List<Player>) :
        RecyclerView.Adapter<PlayerAdapter.MainHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
            val binding = CardPlayerBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

            return MainHolder(binding)
        }

        override fun onBindViewHolder(holder: MainHolder, position: Int) {
            val player = players[holder.adapterPosition]
            holder.bind(player)
        }

        override fun getItemCount(): Int = players.size

        class MainHolder(private val binding : CardPlayerBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(player: Player) {
                binding.playerName.text = player.name.capitalize() + " " + player.last.capitalize()
                binding.playerAge.text = "Age: "+ player.age.toString()
                binding.playerNationality.text = "Nationality: "+player.nationality.capitalize()
                binding.playerClub.text = "Team: "+player.club.capitalize()
                binding.playerPosition.text ="Position: "+ player.position.capitalize()
                binding.numOfMVP.text ="Number of MVP's: " + player.numOfMvp

            }
        }
    }


}