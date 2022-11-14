package ru.mtrefelov.dialer.app.contacts

import ru.mtrefelov.dialer.app.BaseContract
import ru.mtrefelov.dialer.core.Contact

interface ContactsContract {
    interface Presenter : BaseContract.Presenter {
        fun start()

        fun restoreQuery()

        fun filterContacts(query: CharSequence)

        fun saveQuery(query: CharSequence)
    }

    interface View : BaseContract.View<Presenter> {
        fun setContacts(contacts: Iterable<Contact>)

        fun getCurrentQuery(): CharSequence
        fun setCurrentQuery(query: CharSequence)

        fun getSavedQuery(): CharSequence
        fun saveQuery(query: CharSequence)

        fun openDialer(contact: Contact)
    }
}