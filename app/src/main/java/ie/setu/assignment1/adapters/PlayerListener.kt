package ie.setu.assignment1.adapters

import ie.setu.assignment1.models.Player

interface PlayerListener {

        fun onUpdateClick(player: Player)
        fun onDeleteClick(player: Player)

}