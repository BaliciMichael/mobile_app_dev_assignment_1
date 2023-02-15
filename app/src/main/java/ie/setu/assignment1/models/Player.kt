package ie.setu.assignment1.models

data class Player(
    var name: String = "Empty String" ,
    var last: String= "Empty String" ,
    var age: Int = 0,
    var nationality: String= "Empty String" ,
    var mvp: Boolean = false,
    var numOfMvp: Int = 0,
    var club: String = "Empty String" ,
    var position: String = "Empty String",
)
