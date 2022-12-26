package com.aregyan.github.views.userDetails

import androidx.databinding.ObservableParcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aregyan.github.domain.UserDetails
import com.aregyan.github.repository.UserDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

//View model that has user details saved in it
@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val userDetailsRepository: UserDetailsRepository
) : ViewModel() {

    // user details val that will hold Observable Parcelable of user detail
    val userDetails = ObservableParcelable(UserDetails())

    //gets the user detail
    fun getUserDetails(user: String) = userDetailsRepository.getUserDetails(user)

    //refreshes the user detail
    fun refreshUserDetails(user: String) = viewModelScope.launch(Dispatchers.IO) {
        userDetailsRepository.refreshUserDetails(user)
    }

}