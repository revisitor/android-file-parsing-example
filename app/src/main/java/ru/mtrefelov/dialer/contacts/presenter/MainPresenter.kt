package ru.mtrefelov.dialer.contacts.presenter

import ru.mtrefelov.dialer.contacts.MainContract
import ru.mtrefelov.dialer.contacts.model.*

class MainPresenter(view: MainContract.View, contactsJsonArray: String) : MainContract.Presenter {
    private val repository = ContactRepository(contactsJsonArray)
    private var view: MainContract.View? = view

    override fun onViewCreated() {
        view!!.run {
            val contacts = repository.findAll()
            setContacts(contacts)
        }
    }

    override fun onSearchQueryChanged() {
        view!!.run {
            val contacts = with(repository) {
                val query = getSearchQuery()
                if (query.isNotEmpty()) findByOccurrence(query) else findAll()
            }

            setContacts(contacts)
        }
    }

    override fun onDestroy() {
        view = null
    }
}