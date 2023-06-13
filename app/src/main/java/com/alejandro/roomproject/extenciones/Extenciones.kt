package com.alejandro.roomproject.extenciones

import android.app.Activity
import android.content.Context
import android.widget.Toast

fun Activity.myToast(text: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, length).show()
}

fun Context.myToast(text: Int, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, length).show()
}
fun Context.myToast(text: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, length).show()
}

