package com.sinnikhy.attendify.student

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.sinnikhy.attendify.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class StudDataLvlActivity : AppCompatActivity() {
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_PERMISSIONS = 2
    private var currentPhotoPath: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stud_data_lvl)

        val captureButton: Button = findViewById(R.id.capture_button)
        captureButton.setOnClickListener {
            if (checkPermissions()) {
                dispatchTakePictureIntent()
            } else {
                requestPermissions()
            }
        }
    }

    private fun checkPermissions(): Boolean {
        val cameraPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        )
        val storagePermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        return cameraPermission == PackageManager.PERMISSION_GRANTED &&
                storagePermission == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            REQUEST_PERMISSIONS
        )
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.example.android.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as? Bitmap
            if (imageBitmap != null) {
                saveImage(imageBitmap)
            }
        }
    }

    private fun saveImage(bitmap: Bitmap) {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "IMG_$timeStamp.jpg"
        val storageDir = getExternalFilesDir(null)
        val imageFile = File(storageDir, imageFileName)

        try {
            FileOutputStream(imageFile).use { out ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            }
            // Image saved successfully
        } catch (e: IOException) {
            e.printStackTrace()
            // Error occurred while saving the image
        }
    }
}