package com.example.exercise.ui.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.exercise.R

class CommonUtil {

    companion object {
        val FIRST_NAME = "firstName"
        val LAST_NAME = "lastName"
        
        val RESULT_SUCCESS = 1000
        val RESULT_FAILURE = 2000

        fun showDialog(context: Context, title: String?, message: String?) {

            AlertDialog.Builder(context).setTitle(title)
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok) { _, _ -> }
                    .setIcon(android.R.drawable.ic_dialog_alert).show()
        }

        fun isNetworkConnected(context: Context): Boolean {
            if (context == null) return false
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    when {
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                            return true
                        }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                            return true
                        }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                            return true
                        }
                    }
                }
            } else {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                    return true
                }
            }
            return false
        }
        
    fun showInternetConnectionError(actContext:Context){
        val networkErrorTitle = actContext.getString(R.string.network_connection_error_title)
        val networkErrorMessage = actContext.getString(R.string.network_connection_error_message)
        CommonUtil.showDialog(actContext, networkErrorTitle, networkErrorMessage)
    }    
    } // companion object end

}