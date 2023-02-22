package ie.setu.assignment1.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ie.setu.assignment1.adapters.PlayerAdapter
import ie.setu.assignment1.adapters.PlayerListener
import ie.setu.assignment1.databinding.ActivityMainBinding
import ie.setu.assignment1.databinding.CardPlayerBinding
import ie.setu.assignment1.main.MainApp
import ie.setu.assignment1.models.Player
import ie.setu.assignment1.models.PlayerMemStore

class MainActivity : AppCompatActivity(),PlayerListener{
    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingcard: CardPlayerBinding
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
            val intent = Intent(this, MainActivity::class.java)
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