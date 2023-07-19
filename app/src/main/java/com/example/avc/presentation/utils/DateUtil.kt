package com.example.avc.presentation.utils

import java.text.SimpleDateFormat

object DateUtil {
    fun convertLongToTime(time: Long, format: String = "dd/MM/yyyy"): String {
        val format = SimpleDateFormat(format)
        return format.format(time * 1000)
    }
}