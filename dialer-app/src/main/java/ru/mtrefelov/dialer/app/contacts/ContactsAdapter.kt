package ru.mtrefelov.dialer.app.contacts

import android.view.*
import android.widget.TextView

import androidx.recyclerview.widget.*

import ru.mtrefelov.dialer.R
import ru.mtrefelov.dialer.core.Contact

class ContactsAdapter : ListAdapter<Contact, ContactViewHolder>(DiffCallback()) {

    private class DiffCallback : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldContact: Contact, newContact: Contact): Boolean {
            return oldContact == newContact
        }

        override fun areContentsTheSame(oldContact: Contact, newContact: Contact): Boolean {
            return oldContact == newContact
        }
    }

    private var onClickAction: ((Contact) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(contactViewHolder: ContactViewHolder, position: Int) {
        val contact = getItem(position)
        contactViewHolder.apply {
            bind(contact)
            itemView.setOnClickListener {
                onClickAction?.invoke(contact)
            }
        }
    }

    fun setOnClickAction(onClickAction: (Contact) -> Unit) {
        this.onClickAction = onClickAction
    }
}

class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.name_text)
    private val phone: TextView = itemView.findViewById(R.id.phone_text)
    private val type: TextView = itemView.findViewById(R.id.type_text)

    fun bind(contact: Contact) {
        name.text = contact.name
        phone.text = contact.phone
        type.text = contact.type
    }
}