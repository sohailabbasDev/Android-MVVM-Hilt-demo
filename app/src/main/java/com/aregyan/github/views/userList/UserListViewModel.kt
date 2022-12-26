package com.aregyan.github.views.userList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aregyan.github.repository.UserListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

//view model to handle list of of usrs
@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userListRepository: UserListRepository
) : ViewModel() {

    // val to contain users
    val data = userListRepository.users

    init {

        // viewModelScope that gets all the list of users from repo
        viewModelScope.launch(Dispatchers.IO) {
            userListRepository.refreshUserList()
        }
    }

}