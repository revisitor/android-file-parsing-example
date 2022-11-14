package ru.mtrefelov.dialer.app.contacts

import ru.mtrefelov.dialer.core.ContactsRepository

class ContactsPresenter(
    private var view: ContactsContract.View?,
    private val repository: ContactsRepository
) : ContactsContract.Presenter {

    override fun start() {
        requireView {
            setContacts(repository.getAll())
        }
    }

    private fun requireView(action: ContactsContract.View.() -> Unit) {
        view!!.run(action)
    }

    override fun restoreQuery() {
        requireView {
            val query: CharSequence = getSavedQuery()
            setCurrentQuery(query)
        }
    }

    override fun filterContacts(query: CharSequence) {
        view!!.setContacts(
            if (query.isNotEmpty()) {
                repository.filterByOccurrence(query)
            } else {
                repository.getAll()
            }
        )
    }

    override fun saveQuery(query: CharSequence) {
        view!!.saveQuery(query)
    }

    override fun destroy() {
        view = null
    }
}