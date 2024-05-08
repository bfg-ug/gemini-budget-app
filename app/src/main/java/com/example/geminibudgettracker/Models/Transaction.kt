package com.example.geminibudgettracker.Models



data class Transaction(
    val Date: String,
    val Time: String,
    val Description: String,
    val PhoneNumber: String,
    val TransactionType:String,
    val Amount: Double,
    val NewBalance:Double
)
