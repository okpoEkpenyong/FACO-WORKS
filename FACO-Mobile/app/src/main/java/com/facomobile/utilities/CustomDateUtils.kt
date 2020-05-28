package com.stargradegroup.vtuservices.cta.utilities

import android.content.Context
import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

object CustomDateUtils {

    fun getDigitalTime(milliSeconds: Long): String {
        val seconds = milliSeconds / 1000
        val secondsString = if (seconds == 60L) "00" else if (seconds < 10) "0$seconds" else seconds.toString()

        val minutes = seconds / 60
        val minutesString = if (minutes < 10) "0$minutes" else minutes.toString()

        val hours = minutes / 60
        val hoursString = if (hours < 10) "0$hours" else hours.toString()

        val minuteTime = String.format("%s:%s", minutesString, secondsString)

        return if (hours > 0) String.format("%s:%s", hoursString, minuteTime) else minuteTime
    }

    fun getReadableDateString(context: Context, timeInMillis: Long): String {
        val flags = (DateUtils.FORMAT_SHOW_DATE
                or DateUtils.FORMAT_SHOW_YEAR
                or DateUtils.FORMAT_SHOW_WEEKDAY)

        return DateUtils.formatDateTime(context, timeInMillis, flags)
    }

    fun getCorrectDisplayDate(milliseconds: Long): String {
        val eventDayCalendar = Calendar.getInstance()
        eventDayCalendar.timeInMillis = milliseconds

        val currentTime = System.currentTimeMillis()
        val currentDayCalendar = Calendar.getInstance()
        currentDayCalendar.timeInMillis = currentTime

        // If the event is happening in the week the user is currently in
        if (eventDayCalendar.get(Calendar.WEEK_OF_YEAR) == currentDayCalendar.get(Calendar.WEEK_OF_YEAR)) {
            val currentDay = currentDayCalendar.get(Calendar.DAY_OF_WEEK)
            val eventDay = eventDayCalendar.get(Calendar.DAY_OF_WEEK)
            if (eventDay == currentDay) return "today"
            if (eventDay == currentDay + 1) return "tomorrow"
            return if (eventDay == currentDay - 1) "yesterday"
            else eventDayCalendar.getDisplayName(Calendar.DAY_OF_WEEK,
                    Calendar.LONG, Locale.getDefault())!!.toLowerCase()
        } else {
            val day = eventDayCalendar.get(Calendar.DAY_OF_MONTH)
            val month = eventDayCalendar.get(Calendar.MONTH) + 1
            var strMonth = month.toString()
            if (month < 10) strMonth = "0$month"
            val year = eventDayCalendar.get(Calendar.YEAR)
            return "$day/$strMonth/$year"
        }

    }

    fun getCorrectDisplayTime(milliseconds: Long): String {
        val cl = Calendar.getInstance()
        cl.timeInMillis = milliseconds  //here your time in miliseconds
        val hour = cl.get(Calendar.HOUR_OF_DAY)
        val minute = cl.get(Calendar.MINUTE)
        val strHour: String
        val strMinute: String
        if (hour < 10)
            strHour = "0$hour"
        else
            strHour = hour.toString()
        if (minute < 10)
            strMinute = "0$minute"
        else
            strMinute = minute.toString()
        return "$strHour:$strMinute"
    }

    fun getShortCheckOutDate(checkOutTime: Long, expiryDate: Long): String {
        return if (checkOutTime == 0L)
            concatDateAndTime(expiryDate)
        else
            concatDateAndTime(checkOutTime)

    }

    fun getLongCheckInDate(checkedInTime: Long): String {
        return if (checkedInTime == 0L)
            "-"
        else
            getCorrectDisplayTime(checkedInTime) + "\n" + getCorrectDisplayDate(checkedInTime)
    }

    fun getLongCheckOutDate(checkedOutTime: Long, expiryDate: Long): String {
        return if (checkedOutTime == 0L)
            getCorrectDisplayTime(expiryDate) + "\n" + getCorrectDisplayDate(expiryDate)
        else
            getCorrectDisplayTime(checkedOutTime) + "\n" + getCorrectDisplayDate(checkedOutTime)
    }

    fun getShortAddedDate(timestamp: Long): String {
        return concatDateAndTime(timestamp)
    }

    fun getLongAddedDate(timestamp: Long): String {
        return getCorrectDisplayTime(timestamp) + "\n" + getCorrectDisplayDate(timestamp)
    }

    private fun concatDateAndTime(source: Long): String {
        return getCorrectDisplayDate(source) + ", " + getCorrectDisplayTime(source)
    }

    fun getFormattedDateShort(dateTime: Long?): String {
        val newFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        return newFormat.format(Date(dateTime!!))
    }

    fun getFormattedDateSimple(dateTime: Long?): String {
        val newFormat = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())
        return newFormat.format(Date(dateTime!!))
    }

    fun getFormattedDateRegular(dateTime: Long?): String {
        val newFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return newFormat.format(Date(dateTime!!))
    }

    fun getFormattedDateEvent(dateTime: Long?): String {
        val newFormat = SimpleDateFormat("EEE, MMM dd yyyy", Locale.getDefault())
        return newFormat.format(Date(dateTime!!))
    }

    fun getFormattedTimeEvent(time: Long?): String {
        val newFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
        return newFormat.format(Date(time!!))
    }

    fun getFormattedDateOnly(dateTime: Long?): String {
        val newFormat = SimpleDateFormat("dd MMM yy", Locale.getDefault())
        return newFormat.format(Date(dateTime!!))
    }

    fun easyDateFormat(d: Date): String {
        val fmt = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())
        return fmt.format(d)
    }

    fun getAge(year: Int, month: Int, day: Int): Int {
        val dob = Calendar.getInstance()
        val today = Calendar.getInstance()
        dob.set(year, month, day)

        var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) age--

        return age
    }
}
