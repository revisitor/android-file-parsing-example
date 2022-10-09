package ru.mtrefelov.dialer.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.mtrefelov.dialer.R
import ru.mtrefelov.dialer.model.Contact

class ContactRecyclerViewAdapter(
    private val context: Context,
    private val contacts: List<Contact>
) : RecyclerView.Adapter<ContactRecyclerViewAdapter.ContactViewHolder>() {

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.textview_name)
        val phoneTextView: TextView = itemView.findViewById(R.id.textview_phone)
        val typeTextView: TextView = itemView.findViewById(R.id.textview_type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val (name, phone, type) = contacts[position]
        holder.apply {
            nameTextView.text = name
            phoneTextView.text = phone
            typeTextView.text = type
        }
    }

    override fun getItemCount() = contacts.size
}