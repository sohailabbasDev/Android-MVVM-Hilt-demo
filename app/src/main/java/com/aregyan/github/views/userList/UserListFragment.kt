package com.aregyan.github.views.userList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aregyan.github.R
import com.aregyan.github.databinding.FragmentUserListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserListFragment : Fragment() {

    //view model instance
    private val viewModel: UserListViewModel by viewModels()

    //adapter injected
    @Inject
    lateinit var adapter: UsersListAdapter

    //binding
    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // initializing binding, view model and lifecycleOwner, recyclerView
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_user_list, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //attaches list to the adapter getting the data from view model
        viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        // function to perform item click
        adapter.clickListener.onItemClick = {

            //navigation to navigate to other fragment
            findNavController().navigate(UserListFragmentDirections.actionUsersListToUserDetails(it.username))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }

}