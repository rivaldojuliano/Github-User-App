package com.rivzdev.githubuserapp.view.ui.activity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.rivzdev.githubuserapp.databinding.ActivitySettingBinding
import com.rivzdev.githubuserapp.model.data.Reminder
import com.rivzdev.githubuserapp.preference.ReminderPreference
import com.rivzdev.githubuserapp.receiver.AlarmReceiver

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding
    private lateinit var reminder: Reminder
    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reminderPreference = ReminderPreference(this)
        binding.swReminder.isChecked = reminderPreference.getReminder().isReminded

        alarmReceiver = AlarmReceiver()

        binding.swReminder.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                saveReminder(true)
                alarmReceiver.setRepeatingAlarm(this, "Repeating Alarm", "11:08", "Github Reminder")
            } else {
                saveReminder(false)
                alarmReceiver.setCancelAlarm(this)
            }
        }

        settingLanguage()
    }

    private fun saveReminder(state: Boolean) {

        val reminderPreference = ReminderPreference(this)
        reminder = Reminder()

        reminder.isReminded = state
        reminderPreference.setReminder(reminder)
    }

    private fun settingLanguage() {
        binding.btnSelectLanguage.setOnClickListener {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
    }
}