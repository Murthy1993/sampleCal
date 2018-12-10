package com.mobiapp4u.pc.samplecal

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var dBase:SQLiteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dBase = openOrCreateDatabase("cal_db",Context.MODE_PRIVATE,null)
        dBase!!.execSQL("create table if not exists cal_table(_id integer primary key autoincrement , description varchar(500))")

        button_read.setOnClickListener {
            application.assets.open("SampleData.txt").apply {
                textView_data.text = this.readBytes().toString(Charsets.UTF_8)
            }
        }

        button_insert.setOnClickListener {
            val cv = ContentValues()
            cv.put("description",textView_data.text.toString())
          val status =   dBase!!.insert("cal_table",null,cv)
            if(status != -1L){
                Toast.makeText(this@MainActivity,"Data Insert success",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@MainActivity,"Data Insert Failure",Toast.LENGTH_SHORT).show()

            }


        }
    }
}
