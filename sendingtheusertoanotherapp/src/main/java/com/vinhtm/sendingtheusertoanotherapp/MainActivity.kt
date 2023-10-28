package com.vinhtm.sendingtheusertoanotherapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HTTP
import java.util.Calendar


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openCalling()
        openMap()
        openWeb()
        openMail()
        createACalendarEvent()
    }

    private fun openCalling() {
        val btnCalling = findViewById<Button>(R.id.btn_calling)
        btnCalling.setOnClickListener {
            val number = Uri.parse("tel:0971842613")
            val intent = Intent(Intent.ACTION_DIAL, number)
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {}
        }
    }

    private fun openMap() {
        val btnMap = findViewById<Button>(R.id.btn_map)
        btnMap.setOnClickListener {
            val location = Uri.parse("geo:21.036917,105.8243669,15z=14")// z param is zoom level
            val mapIntent = Intent(Intent.ACTION_VIEW, location)
            // Create intent to show chooser
            val chooser = Intent.createChooser(mapIntent,  /* title */null)
            try {
                startActivity(chooser)
            } catch (e: ActivityNotFoundException) {}
        }
    }

    private fun openWeb() {
        val btnWeb = findViewById<Button>(R.id.btn_web)
        btnWeb.setOnClickListener {
            val webpage = Uri.parse("https://www.android.com")
            val webIntent = Intent(Intent.ACTION_VIEW, webpage)
            try {
                startActivity(webIntent)
            } catch (e: ActivityNotFoundException) {}
        }
    }

    private fun openMail() {
        val btnMail = findViewById<Button>(R.id.btn_mail)
        btnMail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SEND)
// The intent does not have a URI, so declare the "text/plain" MIME type
            emailIntent.type = HTTP.PLAIN_TEXT_TYPE
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("thanmanh27vinh@gmail.com")) // recipients
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text")
            emailIntent.putExtra(
                Intent.EXTRA_STREAM,
                Uri.parse("content://path/to/email/attachment")
            )
// You can also attach multiple items by passing an ArrayList of Uris
            try {
                startActivity(emailIntent)
            } catch (e: ActivityNotFoundException) {}
        }
    }

    private fun createACalendarEvent() {
        val btnCreateClendar = findViewById<Button>(R.id.btn_create_calendar)
        btnCreateClendar.setOnClickListener {
            // Event is on January 23, 2021 -- from 7:30 AM to 10:30 AM.
            val calendarIntent = Intent(Intent.ACTION_INSERT, Events.CONTENT_URI)
            val beginTime: Calendar = Calendar.getInstance()
            beginTime.set(2023, 10, 28, 10, 28)
            val endTime: Calendar = Calendar.getInstance()
            endTime.set(2023, 10, 28, 10, 50)
            calendarIntent.putExtra(
                CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                beginTime.timeInMillis
            )
            calendarIntent.putExtra(
                CalendarContract.EXTRA_EVENT_END_TIME,
                endTime.timeInMillis
            )
            calendarIntent.putExtra(Events.TITLE, "Calendar example")
            calendarIntent.putExtra(Events.EVENT_LOCATION, "Example")
            try {
                startActivity(calendarIntent)
            } catch (e: ActivityNotFoundException) {}
        }
    }






}