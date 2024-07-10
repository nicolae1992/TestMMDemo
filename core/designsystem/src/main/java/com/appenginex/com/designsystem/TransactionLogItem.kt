package com.appenginex.com.designsystem

import android.view.SurfaceControl
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appenginex.com.model.Transaction

@Composable
fun TransactionLogItem(
    modifier: Modifier = Modifier,
    transaction: Transaction
) {
    Column(modifier = modifier.padding(8.dp)) {
        Text("Time: ${transaction.time}")
        Text("Amount: ${transaction.amount}")
        Text("Recipient: ${transaction.recipientName}")
        Divider()
    }
}