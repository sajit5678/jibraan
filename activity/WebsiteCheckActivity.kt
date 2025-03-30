package com.buzbuz.smartautoclicker.activity

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.buzbuz.smartautoclicker.R
import com.buzbuz.smartautoclicker.core.processing.data.CoordinatePoints
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class WebsiteCheckActivity : AppCompatActivity() {

    val cordinates = CoordinatePoints()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_website_check)

        // Replace the URL with the website you want to check
        val websiteUrl = cordinates.mypoint



        val webView: WebView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient() // Handle page navigation within WebView

        // Load your website URL here
        webView.loadUrl(websiteUrl)

        // Execute the AsyncTask to perform the website check
//        WebsiteCheckTask().execute(websiteUrl)
    }

    private inner class WebsiteCheckTask : AsyncTask<String, Void, Int>() {
        override fun doInBackground(vararg params: String): Int {
            val websiteUrl = params[0]

            try {
                val url = URL(websiteUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"

                // Get the HTTP response code
                val responseCode = connection.responseCode

                // Read and log the response content (optional)
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val content = StringBuilder()
                var line: String?

                while (reader.readLine().also { line = it } != null) {
                    content.append(line).append("\n")
                }

                Log.d("WebsiteCheck", "Response Content:\n$content")

                return responseCode
            } catch (e: Exception) {
                Log.e("WebsiteCheck", "Error checking website", e)
                return -1  // Return -1 if an error occurs
            }
        }

        override fun onPostExecute(result: Int) {
            // Handle the result (response code)
            if (result == HttpURLConnection.HTTP_OK) {
                Log.d("WebsiteCheck", "Website is reachable (HTTP OK)")
            } else {
                Log.d("WebsiteCheck", "Website is not reachable (HTTP $result)")
            }
        }
    }
}
