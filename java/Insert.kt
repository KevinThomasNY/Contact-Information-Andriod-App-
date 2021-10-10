package com.example.finalproject

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.View
import kotlinx.android.synthetic.main.activity_insert.*

class Insert : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

    }
    inner class myAsync: AsyncTask<Contacts, Unit, Unit>() {
        override fun doInBackground(vararg p0: Contacts) {
            val myDb= DataBaseManager(applicationContext)
            myDb.writableDatabase
            myDb.insert(p0[0])
            return
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            finish()
        }
    }

    fun insertIntoDb(view: View) {
        if(isEmpty(numberEditText.text) )
        {
            numberEditText.hint = "MUST PUT IN VALUE!"
            return
        }
        if(isEmpty(nameEditText.text))
        {
            nameEditText.hint = "MUST PUT IN VALUE"
            return
        }
        if (isEmpty(birthdayEditText.text)){
            birthdayEditText.hint = "MUST PUT IN VALUE"
        }

        myAsync().execute(Contacts(nameEditText.text.toString(),numberEditText.text.toString(),birthdayEditText.text.toString()))

    }

    fun cancel(view: View) {
        finish()
    }
}
