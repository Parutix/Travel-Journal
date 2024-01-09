package com.example.traveljournal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class ShareFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_share, container, false)

        val facebookImageView: ImageView = view.findViewById(R.id.facebook_view)
        val twitterImageView: ImageView = view.findViewById(R.id.twitter_view)
        val instagramImageView: ImageView = view.findViewById(R.id.instagram_view)

        facebookImageView.setOnClickListener {
            shareContent("Share to Facebook", "Text to share")
        }

        twitterImageView.setOnClickListener {
            shareContent("Share to Twitter", "Text to share")
        }

        instagramImageView.setOnClickListener {
            shareContent("Share to Instagram", "Text to share")
        }

        return view
    }

    private fun shareContent(chooserTitle: String, text: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)

        val chooser = Intent.createChooser(intent, chooserTitle)

        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(chooser)
        }
    }
}
