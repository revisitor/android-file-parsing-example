package ru.mtrefelov.dialer.contacts.model

import com.google.gson.Gson

class ContactRepository(contactsJsonArray: String) {
    private val contacts = Gson().fromJson(contactsJsonArray, Array<Contact>::class.java).toList()

    fun findAll() = contacts.toList()

    fun findByOccurrence(text: CharSequence) = if (text.isNotEmpty()) {
        contacts.filter { it.containsText(text) }
    } else {
        findAll()
    }
}