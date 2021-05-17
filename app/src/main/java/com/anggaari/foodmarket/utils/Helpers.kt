package com.anggaari.foodmarket.utils

import android.widget.TextView
import com.anggaari.foodmarket.model.response.Wrapper
import com.google.gson.*
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object Helpers {
    fun TextView.formatPrice(value: String) {
        this.text = getCurrencyIDR(java.lang.Double.parseDouble(value))
    }

    private fun getCurrencyIDR(amount: Double): String {
        val format = DecimalFormat("#,###,###")
        return "IDR " + format.format(amount).replace(",".toRegex(), ".")
    }

    fun getDefaultGson(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .registerTypeAdapter(Date::class.java, JsonDeserializer { json, _, _ ->
                val formatServer = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
                formatServer.timeZone = TimeZone.getTimeZone("UTC")
                formatServer.parse(json.asString)
            })
            .registerTypeAdapter(Date::class.java, JsonSerializer<Date> { src, _, _ ->
                val format = SimpleDateFormat("", Locale.ENGLISH)
                if (src != null) {
                    JsonPrimitive(format.format(src))
                } else {
                    null
                }
            })
            .create()
    }

    fun ResponseBody.parseErrorBody(): Wrapper<*>? {
        val gson = Gson()
        val adapter = gson.getAdapter(Wrapper::class.java)
        try {
            return adapter.fromJson(string())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun Throwable.getErrorBodyMessage(): String {
        return if (this is HttpException) {
            val errorCode = this.code()
            if (errorCode == 405) {
                "Invalid method call"
            } else if (errorCode == 503) {
                "Internal Server Error"
            } else {
                val parseErrorBody = this.response()?.errorBody()!!.parseErrorBody()
                if (parseErrorBody?.meta?.message == null) {
                    "Your request cannot be processed. Try again later"
                } else {
                    parseErrorBody?.meta?.message.toString()
                }
            }

        } else if (this is ConnectException || this is UnknownHostException) {
            "You are offline. Try again later"

        } else {
            return if (this.message == null)
                "Your request cannot be processed. Try again later"
            else if (this.message.equals(""))
                ""
            else
                this.message!!

        }
    }

    fun Long.convertLongToTime(formatDate: String): String {
        val date = Date(this)
        val format = SimpleDateFormat(formatDate)
        return format.format(date)
    }
}