package com.example.ninoaktivities.ui.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.ninoaktivities.R

fun openMap(context: Context, address: String) {
    val encoded = Uri.encode(address)

    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse("geo:0,0?q=$encoded")
    }
    val chooserIntent = Intent.createChooser(
        intent,
        context.getString(R.string.open_map)
    )
    context.startActivity(chooserIntent)
}