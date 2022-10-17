package ru.mtrefelov.dialer.base

interface BaseView<T : BasePresenter> {
    fun setPresenter(presenter: T)
}