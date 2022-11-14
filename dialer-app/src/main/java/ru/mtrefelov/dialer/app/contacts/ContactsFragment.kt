package ru.mtrefelov.dialer.app.contacts

import android.content.*
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*

import ru.mtrefelov.dialer.R
import ru.mtrefelov.dialer.app.DialerApplication
import ru.mtrefelov.dialer.core.Contact

class ContactsFragment : Fragment(R.layout.contacts_fragment), ContactsContract.View {
    private lateinit var contactsAdapter: ContactsAdapter
    private lateinit var searchQuery: EditText
    private lateinit var presenter: ContactsContract.Presenter

    private var searchQueryPreference: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactsAdapter = ContactsAdapter().apply {
            setOnClickAction { openDialer(it) }
        }

        searchQueryPreference = requireActivity().getPreferences(Context.MODE_PRIVATE)

        val repository = (activity?.application as DialerApplication)
            .dependencyContainer
            .contactsRepository

        presenter = ContactsPresenter(this, repository)
    }

    override fun openDialer(contact: Contact) {
        val phone = Uri.parse("tel:${contact.phone}")
        val intent = Intent(Intent.ACTION_DIAL).setData(phone)
        startActivity(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.contacts_recycler_view).apply {
            adapter = contactsAdapter
            layoutManager = LinearLayoutManager(view.context)
        }

        searchQuery = view.findViewById<EditText>(R.id.search_query).apply {
            addTextChangedListener {
                val query = it.toString()
                presenter.filterContacts(query)
            }
        }

        presenter.start()
    }

    override fun onStart() {
        super.onStart()
        presenter.restoreQuery()
    }

    override fun onPause() {
        presenter.saveQuery(getCurrentQuery())
        super.onPause()
    }

    override fun getCurrentQuery(): CharSequence {
        return searchQuery.text.trim()
    }

    override fun onDetach() {
        searchQueryPreference = null
        super.onDetach()
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    override fun setContacts(contacts: Iterable<Contact>) {
        contactsAdapter.submitList(contacts.toList())
    }

    override fun setCurrentQuery(query: CharSequence) {
        searchQuery.setText(query)
    }

    override fun getSavedQuery(): CharSequence {
        val preferenceKey = resources.getString(R.string.preference_search_filter)
        return searchQueryPreference?.getString(preferenceKey, "") ?: ""
    }

    override fun saveQuery(query: CharSequence) {
        val preferenceKey = resources.getString(R.string.preference_search_filter)
        searchQueryPreference!!.edit()
            .putString(preferenceKey, query.toString())
            .apply()
    }

    override fun setPresenter(presenter: ContactsContract.Presenter) {
        this.presenter = presenter
    }
}
