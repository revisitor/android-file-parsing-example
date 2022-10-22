package ru.mtrefelov.dialer.contacts.view.adapter

import ru.mtrefelov.dialer.contacts.model.Contact

interface ContactClickListener {
    fun onContactClicked(contact: Contact)
}