package ie.setu.assignment1.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ie.setu.assignment1.adapters.PlayerAdapter
import ie.setu.assignment1.adapters.PlayerListener
import ie.setu.assignment1.databinding.ActivityMainBinding
import ie.setu.assignment1.databinding.CardPlayerBinding
import ie.setu.assignment1.main.MainApp
import ie.setu.assignment1.models.Player

class MainActivity : AppCompatActivity(),PlayerListener{
    private lateinit var binding: ActivityMainBinding
    private lateinit var binding1: CardPlayerBinding
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MainApp

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter=PlayerAdapter(app.players.findAll(),this)
        binding.addPlayerButton.setOnClickListener { val intent=Intent(this, AddPlayer::class.java)
                startActivity(intent)
        }
    }
    override fun onPlayerClick(player: Player) {
        val launcherIntent = Intent(this, AddPlayer::class.java)
            launcherIntent.putExtra("player_edit", player)
            getClickResult.launch(launcherIntent)


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