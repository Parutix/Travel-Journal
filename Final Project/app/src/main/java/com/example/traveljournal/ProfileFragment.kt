package com.example.traveljournal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.traveljournal.databinding.FragmentLoginBinding
import com.example.traveljournal.databinding.FragmentProfileBinding
import com.example.traveljournal.viewmodels.LoginViewModel
import com.example.traveljournal.viewmodels.ProfileViewModel


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var db: Database

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        viewModel = ProfileViewModel()
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        db = DatabaseManager.getDatabase(requireContext())

        viewModel.full_name.value = db.logged_user_lastname + " " + db.logged_user_firstname
        viewModel.email.value = db.logged_user_email
        return binding.root
    }
}