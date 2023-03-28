package ie.setu.assignment1.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import ie.setu.assignment1.R
import ie.setu.assignment1.adapters.PlayerAdapter
import ie.setu.assignment1.adapters.PlayerListener
import ie.setu.assignment1.databinding.PlayerListMainBinding
import ie.setu.assignment1.databinding.CardPlayerBinding
import ie.setu.assignment1.main.MainApp
import ie.setu.assignment1.models.Player

class PlayerListMain : AppCompatActivity(),PlayerListener{
    private lateinit var binding: PlayerListMainBinding
    lateinit var app: MainApp
    private lateinit var team: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MainApp

        binding = PlayerListMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter=PlayerAdapter(app.players.findAll(),this)
        binding.addPlayerButton.setOnClickListener { val intent=Intent(this, AddPlayer::class.java)
                startActivity(intent)

        }
        team =  binding.teamSpinner
        val teams = arrayOf(
            "Any",
            "Atlanta Hawks",
            "Boston Celtics",
            "Brooklyn Nets",
            "Charlotte Hornets",
            "Chicago Bulls",
            "Cleveland Cavaliers",
            "Dallas Mavericks",
            "Denver Nuggets",
            "Detroit Pistons",
            "Golden State Warriors",
            "Houston Rockets",
            "Indiana Pacers",
            "Los Angeles Clippers",
            "Los Angeles Lakers",
            "Memphis Grizzlies",
            "Miami Heat",
            "Milwaukee Bucks",
            "Minnesota Timberwolves",
            "New Orleans Pelicans",
            "New York Knicks",
            "Oklahoma City Thunder",
            "Orlando Magic",
            "Philadelphia 76ers",
            "Phoenix Suns",
            "Portland Trail Blazers",
            "Sacramento Kings",
            "San Antonio Spurs",
            "Toronto Raptors",
            "Utah Jazz",
            "Washington Wizards"
        )
        val adapter = ArrayAdapter(this, R.layout.spinner_layout, teams)
        adapter.setDropDownViewResource(R.layout.spinner_layout)
        team.adapter = adapter

        binding.searchbutton.setOnClickListener{
            val selected = team.selectedItem.toString()
            filterSearch(selected)
        }
    }

    fun filterSearch(selectedTeam:String){

        if(selectedTeam == "Any"){
            binding.recyclerView.adapter = PlayerAdapter(app.players.findAll(),this)


        }
        else {
            val filteredPlayers = app.players.findAll().filter { it.club == selectedTeam }
            binding.recyclerView.adapter = PlayerAdapter(filteredPlayers, this)


        }

    }


    override fun onUpdateClick(player: Player) {
        val launcherIntent = Intent(this, AddPlayer::class.java)
        launcherIntent.putExtra("player_edit", player)
            getClickResult.launch(launcherIntent)


    }

    override fun onDeleteClick(player: Player) {

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to delete ${player.name.capitalize()} ${player.last.capitalize()}?")
        builder.setPositiveButton("Yes") { _, _ ->
            app.players.removePlayer(player.id)
            Toast.makeText(this, "${player.name.capitalize()} ${player.last.capitalize()} has been successfully deleted", Toast.LENGTH_SHORT)
                .show()
            Thread.sleep(20)
            val intent = Intent(this, PlayerListMain::class.java)
            startActivity(intent)
        }
        //nothing happens when no is clicked
        builder.setNegativeButton("No") { _, _ -> }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }



    private val getClickResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app!!.players.findAll().size)
            }
        }



}