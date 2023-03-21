package ie.setu.assignment1.helpers
import android.content.Context
import android.content.Intent
import android.util.Log.e
import androidx.activity.result.ActivityResultLauncher
import ie.setu.assignment1.R

import java.io.*

fun showImagePicker(intentLauncher : ActivityResultLauncher<Intent>) {
    var chooseFile = Intent(Intent.ACTION_OPEN_DOCUMENT)
    chooseFile.type = "image/*"
    chooseFile = Intent.createChooser(chooseFile, R.string.select_image.toString())
    intentLauncher.launch(chooseFile)
}
