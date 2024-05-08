package com.example.geminibudgettracker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.geminibudgettracker.Repository.SmsRepository
import com.example.geminibudgettracker.ViewModel.FinanceTrackerViewModel
import com.example.geminibudgettracker.Views.Homepage
import com.example.geminibudgettracker.ui.theme.GeminiBudgetTrackerTheme
import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel



class MainActivity : ComponentActivity() {
    private lateinit var financeTrackerViewModel: FinanceTrackerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val smsRepository = SmsRepository(this)
        val generativeModel = GenerativeModel(
            modelName = "gemini-pro",
            apiKey = "AIzaSyCvqSOI9LMU8B1J3_gZivDKjeQ5M_VDhao"
        )

        financeTrackerViewModel = FinanceTrackerViewModel(smsRepository, generativeModel)

        setContent {
            GeminiBudgetTrackerTheme {
                Homepage(financeTrackerViewModel)
            }
        }
    }
}

