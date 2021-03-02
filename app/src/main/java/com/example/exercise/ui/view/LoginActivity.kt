package com.example.exercise.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.example.exercise.R
import com.example.exercise.databinding.LoginActivityBinding
import com.example.exercise.ui.MainApplication
import com.example.exercise.ui.util.CommonUtil
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginActivityBinding
    private val TAG: String = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.firstNameEditText.addTextChangedListener { charSequence ->
            if (charSequence.toString().isNotEmpty() && binding.lastNameEditText.text != null && binding.lastNameEditText.text!!.isNotEmpty())
                enableOrDisableSubmitButton(true)
            else {
                enableOrDisableSubmitButton(false)
            }
        }

        binding.firstNameEditText.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View?, hasFocus: Boolean) {
                Log.d(TAG, "firstNameEditText onFocusChange is called , hasFocus = " + hasFocus)
                binding.firstName.setTextColor(getColorBasedOnFocus(hasFocus))
            }
        }

        binding.lastNameEditText.addTextChangedListener { charSequence ->
            if (charSequence.toString().isNotEmpty() && binding.firstNameEditText.text != null && binding.firstNameEditText.text!!.isNotEmpty())
                enableOrDisableSubmitButton(true)
            else {
                enableOrDisableSubmitButton(false)
            }
        }


        binding.lastNameEditText.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View?, hasFocus: Boolean) {
                Log.d(TAG, "lastNameEditText onFocusChange is called ")
                binding.lastName.setTextColor(getColorBasedOnFocus(hasFocus))
            }
        }

        binding.submitButton.setOnClickListener { view: View ->

            var firstName: String = binding.firstNameEditText.text.toString()
            var lastName = binding.lastNameEditText.text.toString()
            if (!isValidName(firstName)) {
                val firstNameErrorTitle = resources.getString(R.string.firs_name_error_title)
                val firstNameErrorMessage = resources.getString(R.string.firs_name_error_message)
                CommonUtil.showDialog(this@LoginActivity, firstNameErrorTitle, firstNameErrorMessage)
            } else if (!isValidName(lastName)) {
                val lastNameErrorTitle = resources.getString(R.string.last_name_error_title)
                val lasttNameErrorMessage = resources.getString(R.string.last_name_error_message)
                CommonUtil.showDialog(this@LoginActivity, lastNameErrorTitle, lasttNameErrorMessage)
            } else if (!CommonUtil.isNetworkConnected(MainApplication.applicationContext())) {
                CommonUtil.showInternetConnectionError(this@LoginActivity)
            } else {
                val intent = Intent(this, JokesDisplayActivity::class.java)
                intent.putExtra(CommonUtil.FIRST_NAME, firstName)
                intent.putExtra(CommonUtil.LAST_NAME, lastName)
                startActivity(intent)
            }
        }
    }

    fun enableOrDisableSubmitButton(flag: Boolean) {
        binding.submitButton.isEnabled = flag
    }

    fun getColorBasedOnFocus(hasFocus: Boolean): Int {
        val color: Int
        if (hasFocus) {
            color = ContextCompat.getColor(applicationContext, R.color.focus_color)
        } else {
            color = ContextCompat.getColor(applicationContext, R.color.disabled_color)
        }
        return color
    }

    fun isValidName(name: String): Boolean {
        val inputStr: String = name
        val strBuffer = StringBuffer()
        strBuffer.append("^[a-zA-Z\\s]*$")
        val pattern: Pattern = Pattern.compile(String(strBuffer))
        val matcher: Matcher = pattern.matcher(inputStr)
        return matcher.matches()
    }

}