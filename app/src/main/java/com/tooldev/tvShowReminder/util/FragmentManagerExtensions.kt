package com.tooldev.tvShowReminder.util

import android.animation.TimeInterpolator
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.core.view.animation.PathInterpolatorCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionSet

inline fun FragmentManager.inTransactionAdd(func: FragmentTransaction.()-> FragmentTransaction){
    beginTransaction().func().addToBackStack("").commit()
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.()-> FragmentTransaction){
    beginTransaction().func().commit()
}

