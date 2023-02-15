package ie.setu.assignment1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ie.setu.assignment1.databinding.ActivityMainBinding
import ie.setu.assignment1.activities.AddPlayer
import ie.setu.assignment1.main.MainApp

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var app: MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MainApp
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addPlayerButton.setOnClickListener { val intent=Intent(this, AddPlayer::class.java)
                startActivity(intent)
        }
    }


}