package com.example.traveljournal

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var image: ImageView
    private lateinit var text : TextView
    private lateinit var loginFragment: LoginFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        image = findViewById(R.id.splash_book)
        text = findViewById(R.id.splash_text)
        image.alpha = 0f
        image.animate().setDuration(2000).alpha(1f).withEndAction {
            image.visibility = View.GONE
            text.visibility = View.GONE
            showLoginFragment()
        }
    }

    private fun showLoginFragment() {
        loginFragment = LoginFragment()

        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, loginFragment)
            .commit()
    }
}
