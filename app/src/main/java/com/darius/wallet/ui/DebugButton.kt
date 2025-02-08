package com.darius.wallet.ui

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.darius.wallet.BuildConfig

@Composable
fun DebugButton(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    if (BuildConfig.DEBUG) {
        Text(
            text = "debug",
            style = MaterialTheme.typography.labelSmall,
            modifier = modifier
                .clickable {
                    val intent = Intent(
                        context,
                        Class.forName("com.darius.wallet.DebugActivity"),
                    )
                    startActivity(context, intent, null)
                }
                .padding(start = 8.dp)
        )
    }
}

