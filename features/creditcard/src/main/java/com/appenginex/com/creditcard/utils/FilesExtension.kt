package com.appenginex.com.creditcard.utils

import android.content.Context
import android.os.Environment
import android.view.SurfaceControl
import com.appenginex.com.model.Transaction
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.IOException
import java.io.InputStreamReader

const val fileName = "transaction_logs.txt"

fun writeLogsToFile(context: Context, logs: List<Transaction>) {
    val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName)

    try {
        if (!file.exists()) {
            file.createNewFile()
        }
        val fileWriter = FileOutputStream(file, true)
        logs.forEach { log ->
            fileWriter.write("${log.time},${log.amount},${log.recipientName},${log.cardNumber}\n".toByteArray())
        }
        fileWriter.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}


fun readLogsFromFile(context: Context): List<Transaction> {
    val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName)
    val transactions = mutableListOf<Transaction>()

    try {
        val fileInputStream = FileInputStream(file)
        val inputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        var line = bufferedReader.readLine()
        while (line != null) {
            val parts = line.split(",")
            if (parts.size == 4) {
                val transaction = Transaction(parts[0], parts[1], parts[2], parts[3])
                transactions.add(transaction)
            }
            line = bufferedReader.readLine()
        }
        bufferedReader.close()
        inputStreamReader.close()
        fileInputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return transactions
}
