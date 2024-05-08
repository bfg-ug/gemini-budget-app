package com.example.geminibudgettracker.ViewModel

import android.app.Application
import android.content.ContentResolver
import android.database.Cursor
import android.provider.Telephony
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geminibudgettracker.Models.MainAppState
import com.example.geminibudgettracker.Repository.SmsRepository
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.asTextOrNull
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlin.math.log


class FinanceTrackerViewModel(private val smsRepository: SmsRepository, private val model: GenerativeModel):ViewModel() {

    fun consolidateData(){
        CoroutineScope(IO).launch {
            val consolidate = smsRepository.checkMessagesForTransaction(model)

        }
    }
}

