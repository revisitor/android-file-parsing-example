package ru.mtrefelov.dialer.contacts.model

import com.google.gson.Gson

class ContactRepository(contactsJsonArray: String) {
    private val contacts = Gson().fromJson(contactsJsonArray, Array<Contact>::class.java).toList()

    fun findAll() = contacts.toList()

    fun findByOccurrence(text: CharSequence) = contacts.filter {
        it.containsText(text)
    }

    private fun Contact.containsText(text: CharSequence): Boolean {
        val ignoreCase = true
        return name.contains(text, ignoreCase) || type.contains(text, ignoreCase)
    }
}