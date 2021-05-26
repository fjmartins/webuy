package com.fjmartins.webuy.base

import android.content.Context
import androidx.test.core.app.ApplicationProvider

open class BaseUnitTest {

    val context: Context = ApplicationProvider.getApplicationContext()
}