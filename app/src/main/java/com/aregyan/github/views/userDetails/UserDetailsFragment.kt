package com.aregyan.github.views.userDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.aregyan.github.R
import com.aregyan.github.databinding.FragmentUserDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

//User details fragment where single user is shown
@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    //view model instance
    private val viewModel: UserDetailsViewModel by viewModels()

    // arguments from previous fragment
    private val args: UserDetailsFragmentArgs by navArgs()

    //binding of fragment views
    private var _binding: FragmentUserDetailsBinding? = null

    //binding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_user_details, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        //root is inflated
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //refreshes user detail from viewmodel
        viewModel.refreshUserDetails(args.user)
    }

    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //This one is view model scope coroutine that gets user details from view model
        viewModel.getUserDetails(args.user).observe(viewLifecycleOwner) {
            viewModel.userDetails.set(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}