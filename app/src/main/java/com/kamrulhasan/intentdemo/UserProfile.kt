package com.kamrulhasan.intentdemo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class UserProfile(val email: Array<String>, val subject: String, val body: String) : Parcelable