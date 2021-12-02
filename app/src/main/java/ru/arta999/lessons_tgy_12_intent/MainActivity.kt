package ru.arta999.lessons_tgy_12_intent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val HELLO_KEY = "hello"

class MainActivity : AppCompatActivity() {

    lateinit var helloTextViewMainActivity: TextView
    lateinit var nextActivityButton: Button
    lateinit var activityBrowser: Button
    lateinit var activityTel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helloTextViewMainActivity = findViewById(R.id.hello_text_view_main_activity)
        val helloValue: String? = intent.extras?.getString(HELLO_KEY)
        helloTextViewMainActivity.text = helloValue

// Кнопка перехода на следующую страницу

        nextActivityButton = findViewById(R.id.next_activity_button)
        nextActivityButton.setOnClickListener {
            val secondActivityIntent: Intent = Intent(this, SecondActivity::class.java)
            secondActivityIntent.putExtra(HELLO_KEY, "Вы зашли на вторую страницу")
            startActivity(secondActivityIntent)
        }

/* Кнопка перехода в браузер и открытия сайта Googl.com

        activityBrowser = findViewById(R.id.activity_browser)
        activityBrowser.setOnClickListener {
            val googleLink = Uri.parse("https://google.com")
            val openBrowserIntent = Intent(Intent.ACTION_VIEW, googleLink)
            startActivity(openBrowserIntent)
        } */

// Кнопка перехода в браузер и при наличии нескольких браузеров давать выбор какой открыть

        activityBrowser = findViewById(R.id.activity_browser)
        activityBrowser.setOnClickListener {
            val Link = Uri.parse("https://google.com")
            val openBrowserIntent = Intent(Intent.ACTION_VIEW, Link)
            val chooser = Intent.createChooser(openBrowserIntent, "Browser")
            startActivity(chooser)
        }

// Кнопка набора телефонного номера

        activityTel = findViewById(R.id.activity_tel)
        activityTel.setOnClickListener {
            val phoneNumberUri = Uri.parse("tel:88005555550")
//            val callIntent = Intent(Intent.ACTION_VIEW, phoneNumberUri) открывает окно набора номера
//            с уже внесенным номером. Хотя следующая строка ACTION_DIAL то же самое делает
            val callIntent = Intent(Intent.ACTION_DIAL, phoneNumberUri)
//          ACTION_DIAL выдает в эмуляторе "no one is on your speed dial yet" "На вашем быстром наборе еще никого нет" в 11 андроиде
//            val callIntent = Intent(Intent.ACTION_CALL, phoneNumberUri)
            startActivity(callIntent)
        }


// +78005555550

    }
}


