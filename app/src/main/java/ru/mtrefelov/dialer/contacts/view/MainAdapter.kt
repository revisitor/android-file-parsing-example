package ru.mtrefelov.dialer.contacts.view

import android.content.Context
import android.view.*
import androidx.recyclerview.widget.RecyclerView

import ru.mtrefelov.dialer.R
import ru.mtrefelov.dialer.contacts.model.Contact

class MainAdapter(private val context: Context, private val contacts: List<Contact>) :
    RecyclerView.Adapter<ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact)
    }

    override fun getItemCount() = contacts.size
}