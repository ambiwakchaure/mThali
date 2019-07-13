package com.example.mthali.other

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class T
{
    companion object
    {
        fun t(message : String)
        {
            Toast.makeText(MyApplication.context,message,Toast.LENGTH_LONG).show()
        }
        //log
        fun e(message : String)
        {
            Log.e("PACKET_SENDER_LOG",message)
        }
        //snack bar
        fun s(view : View, message : String)
        {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            val view = snackbar.view
            val params = view.layoutParams as CoordinatorLayout.LayoutParams
            params.gravity = Gravity.TOP
            view.layoutParams = params
            snackbar.show()

        }
        //check internet connection
        fun isNetworkAvailable(): Boolean
        {
            val connectivityManager = MyApplication.context.getSystemService(Context.CONNECTIVITY_SERVICE)
            return if (connectivityManager is ConnectivityManager) {
                val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                networkInfo?.isConnected ?: false
            } else false
        }

        fun getSystemDateTime(): String? {
            var systemTime: String? = null
            try {
                val df = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                df.setTimeZone(TimeZone.getTimeZone("UTC"));
                systemTime = df.format(Calendar.getInstance().getTime())
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return systemTime
        }
        //validate editext
        fun validateEditext(view : View, editext : EditText, message : String) : Boolean
        {
            if(editext.text.toString().isEmpty())
            {
                s(view,message)
                return false
            }
            else
            {
                return true
            }
        }
        //validate editext
        fun validatePasswordConfirm(view : View, pass : EditText,confpass : EditText, message : String) : Boolean
        {
            if(pass.text.toString().equals(confpass.text.toString()))
            {
                return true
            }
            else
            {
                s(view,message)
                return false

            }
        }
    }
}