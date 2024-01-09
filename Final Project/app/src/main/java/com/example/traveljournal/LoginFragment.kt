package com.example.traveljournal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.traveljournal.databinding.FragmentLoginBinding
import com.example.traveljournal.viewmodels.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var db : Database

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = LoginViewModel()
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        db = DatabaseManager.getDatabase(requireContext())

        binding.loginButton.setOnClickListener {
            val username = binding.usernameInput.text.toString()
            val password = binding.passwordInput.text.toString()
            val result = db.verifyUser(username, password)

            if (result) {
                db.logged_user_username = username
                db.logged_user_email = db.getEmailByUsername(username)
                db.logged_user_firstname = db.getFirstNameByUsername(username)
                db.logged_user_lastname = db.getLastNameByUsername(username)

                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                binding.errorsText.text = "Invalid username or password!"
            }
        }

        binding.registerButton.setOnClickListener {
            val registerFragment = RegisterFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()

            transaction.replace(android.R.id.content, registerFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return binding.root
    }
}
