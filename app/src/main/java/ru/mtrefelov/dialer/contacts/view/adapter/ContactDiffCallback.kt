package ru.mtrefelov.dialer.contacts.view.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.mtrefelov.dialer.contacts.model.Contact

class ContactDiffCallback : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldContact: Contact, newContact: Contact): Boolean {
        return oldContact == newContact
    }

    override fun areContentsTheSame(oldContact: Contact, newContact: Contact): Boolean {
        return oldContact == newContact
    }
}