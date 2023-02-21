package ie.setu.assignment1.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import ie.setu.assignment1.R
import ie.setu.assignment1.databinding.ActivityAddPlayerBinding
import ie.setu.assignment1.main.MainApp
import ie.setu.assignment1.models.Player


class AddPlayer : AppCompatActivity() {
    //private val players = ArrayList<Player>()

    private lateinit var binding: ActivityAddPlayerBinding
    var player = Player()
    lateinit var app: MainApp


    private lateinit var checkboxmvp: CheckBox
    private lateinit var mvpNum: EditText
    private lateinit var position: Spinner
    private lateinit var club: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityAddPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

// if the update card button was clicked
        if (intent.hasExtra("player_edit")) {
            player = intent.extras?.getParcelable("player_edit")!!
            binding.playerNameEditText.setText(player.name)
            binding.lastName.setText(player.last)
            binding.nationalityEditText.setText(player.nationality)
            binding.playerAgeEditText.setText(player.age.toString())
            binding.createPlayerButton.setHint("Update Player")
            binding.addImageButton.setHint("Update Image")
            binding.textView.setText("Update Player Information")
            binding.mvpCheckbox.isChecked = player.mvp
            binding.mvpNum.setText(player.numOfMvp.toString())
            if(binding.mvpCheckbox.isChecked){
               binding.mvpNum.visibility = View.VISIBLE
            }
            else{
                binding.mvpNum.visibility = View.GONE
            }

           // val playerId = player.id
//
           // updatePlayer(playerId)
            addPlayerButtonClicked()
        }







        checkboxmvp = findViewById(R.id.mvp_checkbox)
        mvpNum = findViewById(R.id.mvpNum)

        //adding an if statement that shows a new field if the mvp button is checked
        checkboxmvp.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                mvpNum.visibility = View.VISIBLE
            } else {
                mvpNum.visibility = View.GONE

            }
        }
        // adding choices to the positionSpinner
        position = findViewById(R.id.position)

        val positions =
            arrayOf("Centre", "Power Forward", "Small Forward", "Shooting Guard", "Point Guard")
        val adapter = ArrayAdapter(this, R.layout.spinner_layout, positions)
        adapter.setDropDownViewResource(R.layout.spinner_layout)
        position.adapter = adapter

        //Same thing for the clubs using all of the NBA clubs
        club = findViewById(R.id.club_spinner)
        val clubs = arrayOf(
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
        val adapter2 = ArrayAdapter(this, R.layout.spinner_layout, clubs)
        adapter2.setDropDownViewResource(R.layout.spinner_layout)
        club.adapter = adapter2

        addPlayerButtonClicked()
    }


    private fun addPlayerButtonClicked() {
        val addplayerbutton = binding.createPlayerButton
        addplayerbutton.setOnClickListener {

            val playerNameEditText = findViewById<EditText>(R.id.player_name_edit_text)
            val playerLastName = findViewById<EditText>(R.id.last_name)
            val playerAgeEditText = findViewById<EditText>(R.id.player_age_edit_text)
            val nationalityEditText = findViewById<EditText>(R.id.nationality_edit_text)
            val clubSpinner = findViewById<Spinner>(R.id.club_spinner)
            val positionSpinner = findViewById<Spinner>(R.id.position)
            val mvpCheckBox = findViewById<CheckBox>(R.id.mvp_checkbox)
            val mvpNumEditText = findViewById<EditText>(R.id.mvpNum)

            val playerName = playerNameEditText.text.toString().trim()
            val lastname = playerLastName.text.toString().trim()
            val playerAgeStr = playerAgeEditText.text.toString().trim()
            var playerAge = 0

            try {
                playerAge = playerAgeStr.toInt()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Please enter a valid number for age", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val nationality = nationalityEditText.text.toString().trim()
            val mvp = mvpCheckBox.isChecked
            var numOfMvp = 0// temporary fix
            if (mvp) {
                try {
                    numOfMvp = mvpNumEditText.text.toString().toInt()
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Please enter a valid number of MVPs", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
            }

            val club = clubSpinner.selectedItem.toString()
            val position = positionSpinner.selectedItem.toString()

            if (intent.hasExtra("player_edit")) {
                player = intent.extras?.getParcelable("player_edit")!!
                val newplayername = playerName
                val newplayerlast = lastname
                val newplayerage = playerAge
                val newnationality = nationality
                val newclub = club
                val newposition = position
                val newmvp = mvp
                val newmvpnum= numOfMvp

                val playerId = player.id

                updatePlayer(playerId,newplayername,newplayerlast,newplayerage,newnationality,newmvp,newmvpnum,newclub,newposition)
            } else {
                if (playerName.isEmpty()) {
                    playerNameEditText.setHintTextColor(ContextCompat.getColor(this, R.color.red))
                    //println(ContextCompat.getColor(this,R.color.red).toString())
                    Toast.makeText(this, "Please Enter a Name", Toast.LENGTH_SHORT)
                        .show()

                } else if (lastname.isEmpty()) {
                    playerLastName.setHintTextColor(ContextCompat.getColor(this, R.color.red))
                    Toast.makeText(this, "Please Enter the Last Name", Toast.LENGTH_SHORT)
                        .show()
                } else if (playerAge <= 0) {

                    playerAgeEditText.setHintTextColor(ContextCompat.getColor(this, R.color.red))
                    Toast.makeText(this, "Please a Valid Number", Toast.LENGTH_SHORT)
                        .show()
                } else if (nationality.isEmpty()) {
                    nationalityEditText.setHintTextColor(ContextCompat.getColor(this, R.color.red))
                    Toast.makeText(this, "Please Enter a valid Nationality", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    //increments the id everytime a player is added
                    val lastPlayerId = app!!.players.findAll().lastOrNull()?.id ?: 0
                    val id = lastPlayerId + 1
                    createPlayer(id, playerName, lastname, playerAge, nationality, mvp, numOfMvp, club, position)
                }

            }
        }
    }


    fun createPlayer(id: Long, playerName: String, lastname: String, playerAge: Int, nationality: String, mvp: Boolean, numOfMvp: Int, club: String, position: String
    ) {
        //code that adds all the inputted information into an arrayList
        Toast.makeText(this, "Player has been added", Toast.LENGTH_SHORT).show()
        val newPlayer =
            Player(id, playerName, lastname, playerAge, nationality, mvp, numOfMvp, club, position)
        app!!.players.create(newPlayer)
        val intent = Intent(this, MainActivity::class.java)
        Thread.sleep(200)
        startActivity(intent)


    }

    private fun updatePlayer(playerId: Long,playerName: String,lastname: String,playerAge: Int, nationality: String, mvp: Boolean, numOfMvp: Int, club: String, position: String) {
        val player = app.players.findById(playerId)

        if (player != null) {
            app.players.update(playerId,playerName,lastname,playerAge,nationality,mvp,numOfMvp,club,position)

            Toast.makeText(this, "Player updated successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            Thread.sleep(200)
            startActivity(intent)
        }


    }
}





