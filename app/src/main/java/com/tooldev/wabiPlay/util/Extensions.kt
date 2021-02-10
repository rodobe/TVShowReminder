package com.tooldev.wabiPlay.util

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.inTransactionAdd(func: FragmentTransaction.()-> FragmentTransaction){
    beginTransaction().func().addToBackStack("").commit()
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.()-> FragmentTransaction){
    beginTransaction().func().commit()
}