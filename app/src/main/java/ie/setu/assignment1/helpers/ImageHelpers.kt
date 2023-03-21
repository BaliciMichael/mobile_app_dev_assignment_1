package ie.setu.assignment1.helpers
import android.content.Context
import android.content.Intent
import android.util.Log.e
import androidx.activity.result.ActivityResultLauncher
import ie.setu.assignment1.R

import java.io.*

fun showImagePicker(intentLauncher: ActivityResultLauncher<Intent>, context: Context) {
    var imagePickerTargetIntent = Intent()

    imagePickerTargetIntent.action = Intent.ACTION_OPEN_DOCUMENT
    imagePickerTargetIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
    imagePickerTargetIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    imagePickerTargetIntent.type = "image/*"
    imagePickerTargetIntent = Intent.createChooser(imagePickerTargetIntent,
        context.getString(R.string.select_image))
    intentLauncher.launch(imagePickerTargetIntent)
}
