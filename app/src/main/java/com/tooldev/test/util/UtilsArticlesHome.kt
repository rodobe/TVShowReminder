package com.tooldev.test.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.tooldev.test.data.model.response.Data
import com.tooldev.test.data.model.response.Hit

object UtilsArticlesHome {

    fun creatMutableListHits(data: Data): MutableList<Hit>{
        val hitMutable: MutableList<Hit> = mutableListOf()
        for (hits in data.hits){
            hitMutable.add(hits)
        }
        return hitMutable
    }

    fun getConnectionType(context: Context?): Boolean{
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            cm.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    return hasTransport(NetworkCapabilities.TRANSPORT_WIFI) or hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) or hasTransport(NetworkCapabilities.TRANSPORT_VPN)
                }
            }
        } else{
            cm.activeNetworkInfo?.run {
                return type == ConnectivityManager.TYPE_WIFI or ConnectivityManager.TYPE_MOBILE or ConnectivityManager.TYPE_VPN
            }
        }
        return false
    }

}