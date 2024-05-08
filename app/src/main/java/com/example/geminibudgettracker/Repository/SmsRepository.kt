package com.example.geminibudgettracker.Repository

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.provider.Telephony
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.geminibudgettracker.Models.Transaction
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.asTextOrNull
import com.google.ai.client.generativeai.type.content
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class SmsRepository(context: Context) {


    private val contentResolver: ContentResolver = context.contentResolver

    fun getRecentMessages(): List<String> {
        val messages = mutableListOf<String>()
        val cursor: Cursor? = contentResolver.query(
            Telephony.Sms.CONTENT_URI,
            null,
            null,
            null,
            "${Telephony.Sms.DATE} DESC LIMIT 10"
        )


        cursor?.use { c ->
            while (c.moveToNext()) {
                val sender = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.ADDRESS))
                val messageBody = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.BODY))
                messages.add("From: $sender\n$messageBody")
            }
        }
        cursor?.close()
        Log.d("sucess", messages.toString())
        return messages
    }


    suspend fun checkMessagesForTransaction(model: GenerativeModel):List<Transaction?> {
        val responselist = mutableListOf<Transaction?>()
        val response = model.generateContent(
            content {
                text("Go through the list of string provided and determine whether a transaction occurred and if consolidate the data into json with the following fields val Date: String,\n" +
                        "Time: String,\n" +
                        "Description: String,\n" +
                        "PhoneNumber: String,\n" +
                        "TransactionType:String,\n" +
                        "Amount: Double,\n" +
                        "NewBalance:Double  return response as a json from the list of string provided"+getRecentMessages())




            }
        )


//        val transactionList = convertStringToTransactionList(response.text.toString())
//
//        println(transactionList)

        Log.d("Response", response.text.toString())

        val transactionList = convertStringToTransactionList(response.text.toString())


        Log.d("Response transaction list", transactionList.toString())
        return responselist
    }


    fun convertStringToTransactionList(input: String): List<Transaction> {
        val transactionList = mutableListOf<Transaction>()
        val regex = """\{([^{}]*)\}""".toRegex()

        regex.findAll(input).forEach { matchResult ->
            val transactionString = matchResult.groupValues.getOrNull(1)
            if (transactionString != null) {
                val transaction = parseTransaction(transactionString)
                transactionList.add(transaction)
            }
        }

        return transactionList
    }

    fun parseTransaction(transactionString: String): Transaction {
        val regex = """"([^"]+)":"([^"]+)"""".toRegex()
        val values = regex.findAll(transactionString)
            .map { it.groupValues[2] }
            .toList()

        return Transaction(
            Date = values.toString(),
            Time = values.getOrElse(1) { "" },
            Description = values.getOrElse(2) { "" },
            PhoneNumber = values.getOrElse(3) { "" },
            TransactionType = values.getOrElse(4) { "" },
            Amount = values.getOrElse(5) { "0.0" }.toDouble(),
            NewBalance = values.getOrElse(6) { "0.0" }.toDouble()
        )
    }






}