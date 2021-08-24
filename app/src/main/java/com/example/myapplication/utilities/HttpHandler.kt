package com.example.myapplication.utilities

import android.os.AsyncTask
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class HttpHandler : AsyncTask<String, Void, String>() {
    // static
    companion object {
        val SELECT_ALL : String = "SELECT_ALL"
    }

    override fun doInBackground(vararg params: String?): String? {
        val uri : String? = params[0]
        val action : String? = params[1]


        try {
            val url : URL = URL(uri)
            val con : HttpURLConnection = url.openConnection() as HttpURLConnection
            val sb : StringBuilder = StringBuilder()

            val br : BufferedReader = BufferedReader(InputStreamReader(con.inputStream))

            var json : String = ""

            while (true) {
                json = br.readLine() ?: break
                sb.append(json + "\n")
            }

//            do{
//                json = br.readLine()
//                sb.append(json + "\n")
//            } while(br.readLine() != null)

            return sb.toString().trim()
        } catch(ex : MalformedURLException) {
            ex.printStackTrace()
        } catch(ex : IOException) {
            ex.printStackTrace()
        }

        return null
    }
}