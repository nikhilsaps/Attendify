package com.sinnikhy.attendify

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.FileProvider
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.oned.Code128Writer
import com.google.zxing.qrcode.QRCodeWriter
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.PageSize
import com.itextpdf.text.pdf.PdfWriter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class StudInfo_ID : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stud_info_id)
        var db = DatabaseHandler(this)
        var datalist = db.readData()

        //box for qr and barcode
        var  barcode:ImageView = findViewById(R.id.barcodeofrollnum)
        var  Qrcode:ImageView = findViewById(R.id.qrcoderollnumshow)
        val text = intent.getStringExtra("STRING_KEY").toString() // The text you want to convert to barcode
        val barcodeBitmap = generateBarcode(text)
        barcode.setImageBitmap(barcodeBitmap)
        val qrCodeBitmap = generateQRCode(text)
        Qrcode.setImageBitmap(qrCodeBitmap)
        //end for qr and bar code
        //creating Id card  data resource locators
        val rollno:TextView= findViewById(R.id.stud_id_roll)
        val name:TextView= findViewById(R.id.stud_id_name)
        val course:TextView= findViewById(R.id.stud_id_course)
        val sem:TextView= findViewById(R.id.stud_id_sem)
        var flag :Boolean =false


        //  now fetch and see data  in the  datalist to filter and see  how it will  work to show extracted data for individual  stud

        for (i in 0..datalist.size-1) {
            if (text == datalist[i].rollno) {
                flag = true
                rollno.text= "Roll NO. : "+datalist[i].rollno
                name.text= "NAME : "+datalist[i].name
                course.text= "COURSE : "+datalist[i].course
                sem.text= "SEM /YEAR  : "+datalist[i].sem
                break;

            }
        }
        if (flag==false){
            Toast.makeText(this,"Student not found",Toast.LENGTH_SHORT).show()
            finish()
        }
        var  printTopdf :ImageButton = findViewById(R.id.stud_id_print)
        printTopdf.setOnClickListener(){
            generatePdfFromCardView()
        }















    }
    private fun generatePdfFromCardView() {
        val cardView: CardView = findViewById(R.id.stud_id_cardView) // Replace with your CardView reference

        val bitmap = viewToBitmap(cardView)
        val pdfFile = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "card_view.pdf")

        // Create a PDF document using the library of your choice (e.g., iText, PdfDocument, etc.)
        // Add the captured CardView image (converted to a Bitmap) to the PDF document
        // Save the PDF document to the specified file location

        // Example using iText library
        val document = Document(PageSize.A4)
        PdfWriter.getInstance(document, FileOutputStream(pdfFile))
        document.open()
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 3000, 1800, false)

        val image = Image.getInstance(bitmapToByteArray(scaledBitmap))
        image.scaleToFit(document.pageSize.width-80, document.pageSize.height)
        document.add(image)
        document.close()

        // Open the PDF file with an external PDF viewer
        openPdfFile(pdfFile)
    }
    private fun viewToBitmap(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    private fun openPdfFile(pdfFile: File) {
        val intent = Intent(Intent.ACTION_VIEW)
        val uri = FileProvider.getUriForFile(this, "com.example.fileprovider", pdfFile)
        intent.setDataAndType(uri, "application/pdf")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(intent)
    }

    private fun generateBarcode(text: String): Bitmap? {
        val barcodeWriter = Code128Writer()
        val bitMatrix: BitMatrix
        bitMatrix = try {
            barcodeWriter.encode(text, BarcodeFormat.CODE_128, 600, 300)
        } catch (e: WriterException) {
            e.printStackTrace()
            return null
        }
        val width = bitMatrix.width
        val height = bitMatrix.height
        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            val offset = y * width
            for (x in 0 until width) {
                pixels[offset + x] = if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
            }
        }
        val barcodeBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        barcodeBitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return barcodeBitmap
    }
    private fun generateQRCode(text: String): Bitmap? {
        val qrCodeWriter = QRCodeWriter()
        val bitMatrix: BitMatrix
        try {
            bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 300, 300)
        } catch (e: WriterException) {
            e.printStackTrace()
            return null
        }

        val width = bitMatrix.width
        val height = bitMatrix.height

        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            val offset = y * width
            for (x in 0 until width) {
                pixels[offset + x] = if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
            }
        }

        val qrCodeBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        qrCodeBitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return qrCodeBitmap
    }
}