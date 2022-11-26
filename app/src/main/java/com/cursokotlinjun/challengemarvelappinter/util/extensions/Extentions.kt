package com.cursokotlinjun.challengemarvelappinter.util.extensions

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursokotlinjun.challengemarvelappinter.domain.model.EventDato
import com.cursokotlinjun.challengemarvelappinter.domain.model.Events
import java.util.regex.Pattern

val EMAIL_PATTERN =
    "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@" +
            "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +
            "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." +
            "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +
            "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|" +
            "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"

fun isEmailValid(email: String): Boolean {
    return Pattern.compile(EMAIL_PATTERN)
        .matcher(email).matches()
}

val PASSWORD_PATTERN =
    "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])" +
            "(?=.*[a-zA-Z])(?=.*[@#$%^&+=])" +
            "(?=\\S+$).{8,}$"

fun isPasswordValid(password: String): Boolean{
    return Pattern.compile(PASSWORD_PATTERN)
        .matcher(password).matches()
}
fun comicsLinearLayoutManager(context: Context) = object : LinearLayoutManager(context) {
    override fun canScrollVertically(): Boolean = false
}

fun String.toYear() = this.substringBefore("-")

fun List<Events>.toEventDatoList() =
    this.map { EventDato(it.id, it.title, it.thumbnail, it.date ?: "--") }
