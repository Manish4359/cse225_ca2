package com.example.cse225_ca2

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    lateinit var uname:EditText
    lateinit var regNo:EditText
    lateinit var setAlarm: Button
    lateinit var timePicker:TimePicker

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timePicker=findViewById<TimePicker>(R.id.timepicker)
        setAlarm = findViewById(R.id.setAlarm)

        setAlarm.setOnClickListener {
            val calender=Calendar.getInstance()
            if(Build.VERSION.SDK_INT>=23){
                calender.set(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH,timePicker.hour,timePicker.minute,0)
            }else{
                calender.set(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH,timePicker.currentHour,timePicker.currentMinute,0)
            }
            setAlarm(calender.timeInMillis)
        }

    }
    fun setAlarm(timeInMIllis:Long){

        var alarmManager=getSystemService(ALARM_SERVICE) as AlarmManager
        val intent=Intent(this,MyBroadCast::class.java)
        var pendingIntent=PendingIntent.getBroadcast(this,0,intent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager.set(AlarmManager.RTC,timeInMIllis,pendingIntent)

        Toast.makeText(this,"alarm is set",Toast.LENGTH_LONG).show()

    }
    class MyBroadCast:BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            Toast.makeText(p0,"Alarm created",Toast.LENGTH_LONG)
        }
    }
}