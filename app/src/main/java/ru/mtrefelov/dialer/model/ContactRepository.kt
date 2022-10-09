package ru.mtrefelov.dialer.model

import android.content.Context
import com.google.gson.Gson

class ContactRepository(applicationContext: Context) {
    private val contacts: List<Contact> = applicationContext.assets.run {
        val inputStream = open("data.json")
        val json: String = inputStream.bufferedReader().use { it.readText() }
        Gson().fromJson(json, Array<Contact>::class.java).toList()
    }

    fun getAll() = contacts.toList()

    fun findByOccurrence(text: CharSequence): List<Contact> {
        return if (text.isNotEmpty()) {
            contacts.filter { it.containsText(text) }
        } else {
            getAll()
        }
    }

    private fun Contact.containsText(text: CharSequence): Boolean {
        val ignoreCase = true
        return name.contains(text, ignoreCase) || type.contains(text, ignoreCase)
    }
}