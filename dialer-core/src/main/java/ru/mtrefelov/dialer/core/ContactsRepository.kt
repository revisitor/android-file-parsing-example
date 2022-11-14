package ru.mtrefelov.dialer.core

interface ContactsRepository {
    fun getAll(): List<Contact>

    fun filterByOccurrence(text: CharSequence): List<Contact>
}