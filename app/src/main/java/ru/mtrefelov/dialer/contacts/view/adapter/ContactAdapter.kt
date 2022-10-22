package ru.mtrefelov.dialer.contacts.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

import ru.mtrefelov.dialer.R
import ru.mtrefelov.dialer.contacts.model.Contact

class ContactAdapter(
    private val context: Context,
    private val clickListener: ContactClickListener
) : ListAdapter<Contact, ContactViewHolder>(ContactDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(contactViewHolder: ContactViewHolder, position: Int) {
        val contact = getItem(position)
        contactViewHolder.apply {
            bind(contact)
            itemView.setOnClickListener {
                clickListener.onContactClicked(contact)
            }
        }
    }
}