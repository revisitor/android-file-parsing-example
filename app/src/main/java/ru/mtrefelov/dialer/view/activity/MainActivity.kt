package ru.mtrefelov.dialer.view.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.*

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*

import ru.mtrefelov.dialer.R
import ru.mtrefelov.dialer.model.*
import ru.mtrefelov.dialer.view.adapter.ContactRecyclerViewAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var searchButton: Button
    private lateinit var searchEditText: EditText
    private lateinit var contactRecyclerView: RecyclerView

    private lateinit var contactRepository: ContactRepository
    private val shownContacts: MutableList<Contact> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactRepository = ContactRepository(applicationContext)
        shownContacts.apply {
            val contacts = contactRepository.getAll()
            addAll(contacts)
        }

        contactRecyclerView = findViewById(R.id.recyclerview)
        contactRecyclerView.apply {
            val activityContext: Context = this@MainActivity
            layoutManager = LinearLayoutManager(activityContext)
            adapter = ContactRecyclerViewAdapter(activityContext, shownContacts)
        }

        searchEditText = findViewById(R.id.edittext_search)
        searchButton = findViewById(R.id.button_search)
        searchButton.setOnClickListener {
            val searchQuery = getSearchQuery()
            val matchingContacts = contactRepository.findByOccurrence(searchQuery)
            replaceContacts(matchingContacts)
            synchronizeRecyclerView()
        }
    }

    private fun getSearchQuery() = searchEditText.text.trim()

    private fun replaceContacts(contacts: Collection<Contact>) {
        shownContacts.apply {
            clear()
            addAll(contacts)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun synchronizeRecyclerView() = contactRecyclerView.adapter?.notifyDataSetChanged()
}