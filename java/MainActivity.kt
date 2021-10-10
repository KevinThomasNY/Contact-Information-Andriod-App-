package com.example.finalproject



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateView()
    }

    override fun onResume() {
        super.onResume()
        updateView()
    }

    fun updateView(): Unit {
        contacts.text = ""
        val myDb = DataBaseManager(applicationContext)
        myDb.writableDatabase
        val listToDispay = myDb.selectAll()
        for (i in listToDispay) {
            val isDrive: String = if (i.contactsNumber.toInt() >= 16) "Yes" else "No"
            val isVote = if (i.contactsNumber.toInt() >= 18) "Yes" else "No"
            val isCarRental = if (i.contactsNumber.toInt() >= 21) "Yes" else "No"

            contacts.append(
                    "Name: ${i.contactsName}   Age: ${i.contactsNumber}   Birthday: ${i.contactsBirthday}\n" +
                            "Whether can drive: ${isDrive}\nWhether can vote: ${isVote}\nWhether can rent a car: ${isCarRental}\n\n"
            )
        }
    }

    fun insertActivity(view: View) {
        val myIntent = Intent(this, Insert::class.java)
        startActivity(myIntent)
    }

    fun onClick(view: View) {
        val myIntent = Intent(this, MapsActivity::class.java)
        startActivity(myIntent)
    }


}
