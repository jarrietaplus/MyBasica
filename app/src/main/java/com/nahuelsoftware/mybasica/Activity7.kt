package com.nahuelsoftware.mybasica

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File
import java.io.File.createTempFile

private const val REQUESTED_CODE = 42 //cualquiera....
//file
private lateinit var photoFile: File
private const val FILE_NAME = "nahuel"
private var orientation = 0
///file

class Activity7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7)

        val btnTakePicture: Button = this.findViewById(R.id.btnTomarFoto)
        val btnRotatePicture: Button = this.findViewById(R.id.btnRotarFoto)
        btnRotatePicture.isEnabled = false

        btnTakePicture.setOnClickListener{
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE)
//file
            photoFile = getPhotoFile(FILE_NAME)
            val fileProvider = FileProvider.getUriForFile(this, "com.nahuelsoftware.fileprovider", photoFile)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
///file
//rotation
//            val ei:ExifInterface = ExifInterface("$photoFile")
//            var orientation:Int = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_UNDEFINED)
//            System.out.println("Orientation: $orientation")
///rotation
            orientation = 0
            if(takePictureIntent.resolveActivity(this.packageManager) != null){
            startActivityForResult(takePictureIntent, REQUESTED_CODE)
                btnRotatePicture.isEnabled = true
            }else{
                Toast.makeText(this, "Cámara no disponible", Toast.LENGTH_SHORT).show()
            }
        }

        btnRotatePicture.setOnClickListener {
            val imageView: ImageView = this.findViewById(R.id.imageView)
            val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
            var rotatedImage = takenImage
            when(orientation){
                0 -> {
                    rotatedImage = rotateImage(takenImage,90f)
                    orientation = 1
                }
                1 -> {
                    rotatedImage = rotateImage(takenImage,180f)
                    orientation = 2
                }
                2 -> {
                    rotatedImage = rotateImage(takenImage,270f)
                    orientation = 3
                }
                3 -> {
                    orientation = 0
                }
            }
            imageView.setImageBitmap(rotatedImage)
        }
    }

    private fun getPhotoFile(fileName: String): File {
        val storageDirectory: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return createTempFile(fileName, ".jpg", storageDirectory)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUESTED_CODE && resultCode == Activity.RESULT_OK){
//file
        // val takenImage = data?.extras?.get("data") as Bitmap
            val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
///file
//rotation
//            val ei:ExifInterface = ExifInterface("$photoFile")
//            var orientation:Int = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_UNDEFINED)
//            System.out.println("Orientation: $orientation")
///rotation
            val imageView: ImageView = this.findViewById(R.id.imageView)
            imageView.setImageBitmap(takenImage)
        }else {
        super.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun rotateImage(source: Bitmap, angle: Float): Bitmap? {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(
            source, 0, 0, source.width, source.height,
            matrix, true
        )
    }
}