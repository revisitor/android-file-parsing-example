package ru.mtrefelov.dialer.contacts.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.*

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*

import ru.mtrefelov.dialer.R
import ru.mtrefelov.dialer.contacts.MainContract
import ru.mtrefelov.dialer.contacts.model.*
import ru.mtrefelov.dialer.contacts.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var searchButton: Button
    private lateinit var searchEditText: EditText
    private lateinit var contactRecyclerView: RecyclerView

    private lateinit var presenter: MainContract.Presenter

    private val shownContacts: MutableList<Contact> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactRecyclerView = findViewById<RecyclerView>(R.id.recyclerview).apply {
            val context: Context = this@MainActivity
            adapter = MainAdapter(context, shownContacts)
            layoutManager = LinearLayoutManager(context)
        }

        searchEditText = findViewById(R.id.edittext_search)
        searchButton = findViewById<Button>(R.id.button_search).apply {
            setOnClickListener {
                presenter.onSearchButtonClicked()
            }
        }

        with(resources) {
            val view: MainContract.View = this@MainActivity
            val json = openRawResource(R.raw.data).bufferedReader().use { it.readText() }
            val presenter = MainPresenter(view, json)
            setPresenter(presenter)
        }

        presenter.onViewCreated()
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }

    override fun getSearchQuery(): CharSequence = searchEditText.text.trim()

    @SuppressLint("NotifyDataSetChanged")
    override fun setContacts(contacts: Collection<Contact>) {
        shownContacts.apply {
            clear()
            addAll(contacts)
        }

        contactRecyclerView.adapter?.notifyDataSetChanged()
    }
}