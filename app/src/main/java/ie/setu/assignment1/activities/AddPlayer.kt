package ie.setu.assignment1.activities

import android.os.Bundle
import android.util.Log.i
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ie.setu.assignment1.R
import ie.setu.assignment1.databinding.ActivityAddPlayerBinding
import ie.setu.assignment1.main.MainApp
import ie.setu.assignment1.models.Player


class AddPlayer : AppCompatActivity() {
    //private val players = ArrayList<Player>()

    private lateinit var binding: ActivityAddPlayerBinding
    var player = Player()
    var app : MainApp? = null



    private lateinit var checkboxmvp: CheckBox
    private lateinit var mvpNum: EditText
    private lateinit var position: Spinner
    private lateinit var club: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_player)
        app = application as MainApp

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

        val positions = arrayOf("Centre", "Power Forward", "Small Forward", "Shooting Guard","Point Guard")
        val adapter = ArrayAdapter(this, R.layout.spinner_layout, positions)
        adapter.setDropDownViewResource(R.layout.spinner_layout)
        position.adapter = adapter

      //Same thing for the clubs using all of the NBA clubs
        club = findViewById(R.id.club_spinner)
       val clubs = arrayOf("Atlanta Hawks", "Boston Celtics", "Brooklyn Nets", "Charlotte Hornets",
            "Chicago Bulls", "Cleveland Cavaliers", "Dallas Mavericks", "Denver Nuggets", "Detroit Pistons",
            "Golden State Warriors", "Houston Rockets", "Indiana Pacers", "Los Angeles Clippers", "Los Angeles Lakers",
            "Memphis Grizzlies", "Miami Heat", "Milwaukee Bucks", "Minnesota Timberwolves", "New Orleans Pelicans",
            "New York Knicks", "Oklahoma City Thunder", "Orlando Magic", "Philadelphia 76ers", "Phoenix Suns",
            "Portland Trail Blazers", "Sacramento Kings", "San Antonio Spurs", "Toronto Raptors", "Utah Jazz",
            "Washington Wizards")
       val adapter2 = ArrayAdapter(this, R.layout.spinner_layout, clubs)
        adapter2.setDropDownViewResource(R.layout.spinner_layout)
        club.adapter = adapter2

        addPlayerButtonClicked()
    }





    private fun addPlayerButtonClicked(){
        val addplayerbutton = findViewById<Button>(R.id.create_player_button)
        addplayerbutton.setOnClickListener {
            val playerNameEditText = findViewById<EditText>(R.id.player_name_edit_text)
            val playerLastName = findViewById<EditText>(R.id.last_name)
            val playerAgeEditText = findViewById<EditText>(R.id.player_age_edit_text)
            val nationalityEditText = findViewById<EditText>(R.id.nationality_edit_text)
            val clubSpinner = findViewById<Spinner>(R.id.club_spinner)
            val positionSpinner = findViewById<Spinner>(R.id.position)
            val mvpCheckBox = findViewById<CheckBox>(R.id.mvp_checkbox)
            val mvpNumEditText = findViewById<EditText>(R.id.mvpNum)

            val playerName = playerNameEditText.text.toString()
            val lastname = playerLastName.text.toString()
            val playerAgeStr = playerAgeEditText.text.toString()
            val playerAge = if (playerAgeStr.isNullOrEmpty()) 0 else playerAgeStr.toInt()



                val nationality = nationalityEditText.text.toString()
                val mvp = mvpCheckBox.isChecked

                val numOfMvp = if (!mvp) 0 else mvpNumEditText.text.toString().toInt()

                val club = clubSpinner.selectedItem.toString()
                val position = positionSpinner.selectedItem.toString()
                if (playerName.isEmpty() || club.isEmpty() || position.isEmpty() || lastname.isEmpty() || playerAge <= 0 || nationality.isEmpty()) {
                    Toast.makeText(this, "Please enter all values", Toast.LENGTH_SHORT).show()
                } else {
                    createPlayer(playerName, lastname, playerAge, nationality, mvp, numOfMvp, club, position)
                }

        }
    }





    fun createPlayer(playerName: String,lastname: String, playerAge: Int, nationality: String, mvp: Boolean,numOfMvp: Int, club: String, position: String) {
        //code that adds all the inputted information into an arrayList


            Toast.makeText(this, "Player has been added", Toast.LENGTH_SHORT).show()
            val newPlayer =
                Player(playerName, lastname, playerAge, nationality, mvp, numOfMvp, club, position)
            app!!.players.add(newPlayer)
            for (p in app!!.players) {
                println("${p} \n")

        }
    }



}


