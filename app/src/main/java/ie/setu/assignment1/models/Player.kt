package ie.setu.assignment1.models
import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Player(
    var id: Long = 0,
    var name: String = "Empty String" ,
    var last: String= "Empty String" ,
    var age: Int = 0,
    var nationality: String= "Empty String" ,
    var mvp: Boolean = false,
    var numOfMvp: Int = 0,
    var club: String = "Empty String" ,
    var position: String = "Empty String",
    var imageUri: Uri= Uri.EMPTY,
) : Parcelable
