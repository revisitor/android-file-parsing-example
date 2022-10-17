package ru.mtrefelov.dialer.contacts.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import ru.mtrefelov.dialer.R
import ru.mtrefelov.dialer.contacts.model.Contact

class ContactViewHolder(root: View) : RecyclerView.ViewHolder(root) {
    private val nameText: TextView = root.findViewById(R.id.textview_name)
    private val phoneText: TextView = root.findViewById(R.id.textview_phone)
    private val typeText: TextView = root.findViewById(R.id.textview_type)

    fun bind(contact: Contact) {
        contact.run {
            nameText.text = name
            phoneText.text = phone
            typeText.text = type
        }
    }
}