package com.example.geminibudgettracker.Views

import CircularRingChart
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.geminibudgettracker.Models.Transaction
import com.example.geminibudgettracker.ViewModel.FinanceTrackerViewModel
import com.example.geminibudgettracker.ui.theme.blue
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content


@Composable
fun Homepage(financeTrackerViewModel: FinanceTrackerViewModel){



    Surface(Modifier.fillMaxSize()){

        Column( horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 16.dp)

        ) {


            Text(text = "My Financial tracker", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 16.dp))


            val data = listOf(
                20f to Color.Blue,
                30f to Color.Green,
                10f to Color.Red,
                40f to Color.Magenta
            )
            CircularRingChart(data = data, total = 100f)


            Text(text = financeTrackerViewModel.consolidateData().toString())



            OutlinedButton(onClick = {
               financeTrackerViewModel.consolidateData()
            }, modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()) {
                Text(text = "Collect Data")
                
            }


        }

    }
}




@Composable
fun transactionItem(transaction: Transaction){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .clip(RoundedCornerShape(15.dp))
        .background(blue)

    ) {



        Column(modifier = Modifier
            .padding(vertical = 8.dp)
            .weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = transaction.TransactionType, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = transaction.Description, fontSize = 12.sp, fontWeight = FontWeight.Light )
        }

        Column(modifier = Modifier
            .padding(vertical = 8.dp)
            .weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = transaction.Amount.toString(), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = transaction.PhoneNumber, fontSize = 12.sp, fontWeight = FontWeight.Light )
        }

        Column(modifier = Modifier
            .padding(vertical = 8.dp)
            .weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = transaction.Time, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = transaction.Date, fontSize = 12.sp, fontWeight = FontWeight.Light )
        }


    }
}




