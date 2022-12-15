package com.kamrulhasan.intentdemo

import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.core.text.bold
import com.kamrulhasan.intentdemo.databinding.ActivityShowMailInfoBinding

private const val TAG = "PreviewMail"

class PreviewMail : AppCompatActivity() {

    private lateinit var binding: ActivityShowMailInfoBinding
    private var previewBackground = true
    private var previewFont = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // binding initialization
        binding = ActivityShowMailInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Catching object
        val userInfo = intent.getParcelableExtra<UserProfile>(Constants.emailObject)!!

        binding.tvEmail.text = SpannableStringBuilder()
            .bold { append(getString(R.string.show_mail)) }
            .append("     ")
            .append(userInfo.email.joinToString(","))
        binding.tvSubject.text = SpannableStringBuilder()
            .bold { append(getString(R.string.show_subject)) }
            .append("   ")
            .append(userInfo.subject.toString())
        binding.tvBody.text = SpannableStringBuilder()
            .bold { append(getString(R.string.show_body)) }
            .append(userInfo.body.toString())

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d(TAG, "Option Created")

        menuInflater.inflate(R.menu.layout_menu, menu)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "Item Selected")
        return when (item.itemId) {
            R.id.menu_item_background -> {
                changeBackground()
                previewBackground = !previewBackground

                return true
            }
            R.id.menu_item_font -> {
                changeFont()
                previewFont = !previewFont
                return true
            }
            else ->
                return super.onOptionsItemSelected(item)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun changeFont() {
        if (previewFont) {
            binding.tvTitle.typeface = resources.getFont(R.font.lobster_font)
            binding.tvEmail.typeface = resources.getFont(R.font.lobster_font)
            binding.tvSubject.typeface = resources.getFont(R.font.lobster_font)
            binding.tvBody.typeface = resources.getFont(R.font.lobster_font)
        } else {
            binding.tvTitle.typeface = resources.getFont(R.font.roboto_font)
            binding.tvEmail.typeface = resources.getFont(R.font.roboto_font)
            binding.tvSubject.typeface = resources.getFont(R.font.roboto_font)
            binding.tvBody.typeface = resources.getFont(R.font.roboto_font)
        }
    }


    private fun changeBackground() {
        if (previewBackground) {

            binding.tvEmail.setBackgroundColor(Color.LTGRAY)
            binding.tvSubject.setBackgroundColor(Color.LTGRAY)
            binding.tvBody.setBackgroundColor(Color.LTGRAY)

        } else {

            binding.tvEmail.setBackgroundColor(resources.getColor(R.color.gray))
            binding.tvSubject.setBackgroundColor(resources.getColor(R.color.gray))
            binding.tvBody.setBackgroundColor(resources.getColor(R.color.gray))

        }

    }
}