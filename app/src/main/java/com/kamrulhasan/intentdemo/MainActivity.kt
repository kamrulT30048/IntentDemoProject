package com.kamrulhasan.intentdemo

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.kamrulhasan.intentdemo.databinding.ActivityMainBinding


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    //@override from menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d(ContentValues.TAG, "Option Created")

        menuInflater.inflate(R.menu.main_layout_menu, menu)
        return true
    }

    //@override from menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        Log.d(ContentValues.TAG, "Item Selected")

        return when (item.itemId) {
            R.id.menu_item_mail -> {

                if( binding.tiMailTo.text == null ){
                    binding.tiMailTo.error = "Required"
                    return false
                }

                composeEmail(
                    binding.tiMailTo.text.toString(),
                    binding.tiMailSubject.text.toString(),
                    binding.tiMailBody.text.toString()
                )

                return true
            }
            R.id.menu_item_preview -> {

                //call passing object function
                passingUserProfile()

                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    // passing object to another activity preview
    private fun passingUserProfile() {

        //assigning UserProfile class object
        val userInfo = UserProfile(
            arrayOf(binding.tiMailTo.text.toString()),
            binding.tiMailSubject.text.toString(),
            binding.tiMailBody.text.toString()
        )

        val intent = Intent(this, PreviewMail::class.java)
        intent.putExtra(Constants.emailObject, userInfo)
        startActivity(intent)
    }

    // passing data to mail app
    private fun composeEmail(address: String, mailSubject: String, mailBody: String) {

        Log.d(TAG, "2nd to: $address, sub: $mailSubject, body: $mailBody")

        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
        intent.putExtra(Intent.EXTRA_SUBJECT, mailSubject)
        intent.putExtra(Intent.EXTRA_TEXT, mailBody)

        //start the action
        startActivity(intent)

    }
}