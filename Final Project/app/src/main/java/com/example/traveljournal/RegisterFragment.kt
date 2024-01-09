package com.example.traveljournal

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.traveljournal.data.UserData
import com.example.traveljournal.databinding.FragmentRegisterBinding
import com.example.traveljournal.viewmodels.RegisterViewModel


class RegisterFragment : Fragment() {

    private lateinit var viewModel : RegisterViewModel
    private lateinit var db : Database
    private lateinit var binding : FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        viewModel = RegisterViewModel()
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        db = DatabaseManager.getDatabase(requireContext())


        binding.registerButton.setOnClickListener {
            val username = binding.usernameInput.text.toString()
            Log.d("RegisterFragment", username)
            val email = binding.emailInput.text.toString()
            val first_name = binding.firstnameInput.text.toString()
            val last_name = binding.lastnameInput.text.toString()
            val password = binding.passwordInput.text.toString()

            val userData = UserData(
                username = username,
                email = email,
                first_name = first_name,
                last_name = last_name,
                password = password
            )


            val result = db.checkRegisterFields(userData)
            binding.errorsText.text = result.second
            if(result.first == true) {
                db.insertUser(userData)
                val loginFragment = LoginFragment()
                val transaction = requireActivity().supportFragmentManager.beginTransaction()

                transaction.replace(android.R.id.content, loginFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }

        return binding.root
    }

}