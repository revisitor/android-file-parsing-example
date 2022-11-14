package ru.mtrefelov.dialer.data

import ru.mtrefelov.dialer.core.*

class ContactsDummyRepository(private val contacts: Iterable<Contact>) : ContactsRepository {
    override fun getAll(): List<Contact> {
        return contacts.toList()
    }

    override fun filterByOccurrence(text: CharSequence): List<Contact> {
        return contacts.filter { it.containsText(text) }
    }
}