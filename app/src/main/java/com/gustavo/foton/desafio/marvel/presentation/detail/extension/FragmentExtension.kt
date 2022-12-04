package com.gustavo.foton.desafio.marvel.presentation.detail.extension

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.showShortToast(@StringRes textResId: Int) =
    Toast.makeText(context, textResId, Toast.LENGTH_SHORT).show()