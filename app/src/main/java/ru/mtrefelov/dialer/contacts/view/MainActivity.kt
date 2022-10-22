package ru.mtrefelov.dialer.contacts.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.EditText

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.*

import ru.mtrefelov.dialer.R
import ru.mtrefelov.dialer.contacts.MainContract
import ru.mtrefelov.dialer.contacts.model.Contact
import ru.mtrefelov.dialer.contacts.presenter.MainPresenter
import ru.mtrefelov.dialer.contacts.view.adapter.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private val activityContext: Context = this

    private val contactClickListener = object : ContactClickListener {
        override fun onContactClicked(contact: Contact) {
            openDialer(contact)
        }
    }

    private lateinit var searchToolbar: Toolbar
    private lateinit var searchText: EditText
    private lateinit var contactRecyclerView: RecyclerView
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var searchFilterPreference: SharedPreferences

    private lateinit var presenter: MainContract.Presenter

    override fun openDialer(contact: Contact) {
        val phoneNumber = Uri.parse("tel:${contact.phone}")
        val dialerIntent = Intent(Intent.ACTION_DIAL).setData(phoneNumber)
        startActivity(dialerIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactRecyclerView = findViewById<RecyclerView>(R.id.recyclerview)
            .apply {
                adapter = ContactAdapter(activityContext, contactClickListener)
                layoutManager = LinearLayoutManager(activityContext)
            }

        contactAdapter = contactRecyclerView.adapter as ContactAdapter

        searchToolbar = findViewById<Toolbar>(R.id.search_toolbar)
            .also { setSupportActionBar(it) }

        setPresenter(MainPresenter(this, getRawContactsJson()))

        searchFilterPreference = getPreferences(Context.MODE_PRIVATE)

        searchText = findViewById<EditText>(R.id.search_text)
            .apply {
                searchFilterPreference.getSearchFilter()?.let { setText(it) }
                addTextChangedListener {
                    searchFilterPreference.saveSearchFilter(it.toString())
                    presenter.onSearchQueryChanged()
                }
            }


        presenter.onViewCreated()
    }

    private fun SharedPreferences.getSearchFilter(): String? {
        val preferenceKey = resources.getString(R.string.preference_search_filter)
        return getString(preferenceKey, "")
    }

    private fun SharedPreferences.saveSearchFilter(searchFilter: String) {
        val preferenceKey = resources.getString(R.string.preference_search_filter)
        edit().putString(preferenceKey, searchFilter).apply()
    }

    private fun getRawContactsJson(): String {
        return resources.openRawResource(R.raw.data).use { resourceInputStream ->
            resourceInputStream.bufferedReader().use { reader ->
                reader.readText()
            }
        }
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }

    override fun getSearchQuery() = searchText.text.trim()

    override fun setContacts(contacts: List<Contact>) {
        contactAdapter.submitList(contacts)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}