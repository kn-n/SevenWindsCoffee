package ru.kn_n.sevenwindscoffee.utils.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import toothpick.Toothpick
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor() :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return Toothpick.openScope(Scopes.APP_SCOPE).getInstance(modelClass) as T
    }
}