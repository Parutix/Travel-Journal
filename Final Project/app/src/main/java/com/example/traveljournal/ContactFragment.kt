package com.example.traveljournal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

class ContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact, container, false)

        val composeEmailButton: Button = view.findViewById(R.id.composeEmailButton)
        composeEmailButton.setOnClickListener {
            composeEmail()
        }

        return view
    }

    private fun composeEmail() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("razvanlungeanu21@gmail.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Travel Journal App")

        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(requireContext(), "No email app found", Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = ContactFragment()
    }
}
