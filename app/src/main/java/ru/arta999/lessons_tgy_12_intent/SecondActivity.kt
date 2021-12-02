package ru.arta999.lessons_tgy_12_intent

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val HELLO_KEY = "hello"

class SecondActivity : AppCompatActivity() {

    lateinit var helloTextView: TextView
    lateinit var backActivityButton: Button
    lateinit var activityImage: Button
    private val REQUEST_TAKE_PHOTO = 1
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        helloTextView = findViewById(R.id.hello_text_view)

        val helloValue: String? = intent.extras?.getString(HELLO_KEY)
        helloTextView.text = helloValue

        backActivityButton = findViewById(R.id.back_activity_button)

        backActivityButton.setOnClickListener {
            val mainActivityIntent: Intent = Intent(this, MainActivity::class.java)
            mainActivityIntent.putExtra(HELLO_KEY, "А теперь вернулись на главную")
//          mainActivityIntent.putExtra(HELLO_KEY, null)   если так написать, то будет передавать пустоту
//          mainActivityIntent.putExtra(HELLO_KEY, null as String?) а если так, то передаст hello, которое в 9 строке
            startActivity(mainActivityIntent)
        }

        activityImage = findViewById(R.id.activity_image)
        activityImage.setOnClickListener {

                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                try { startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }


        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
                // Фотка сделана, извлекаем миниатюру картинки
                val thumbnailBitmap = data?.extras?.get("data") as Bitmap
                imageView.setImageBitmap(thumbnailBitmap)
            }



        }

    }
}