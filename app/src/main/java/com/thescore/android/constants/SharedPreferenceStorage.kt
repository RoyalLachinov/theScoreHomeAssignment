package com.thescore.android.constants

import android.content.Context
import android.content.SharedPreferences
import com.thescore.android.di.TheScoreSingletonAnnotation
import javax.inject.Inject

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

@TheScoreSingletonAnnotation
class SharedPreferenceStorage @Inject constructor(val context: Context) {

    private fun getSharedPreferences(): SharedPreferences =
        context.getSharedPreferences(CoreConstants.CONFIG, Context.MODE_PRIVATE)

    fun saveIntegerValue(key: String, value: Int) {
        getSharedPreferences().edit().putInt(key, value).apply()
    }

    fun getIntegerValue(key: String): Int {
        return getSharedPreferences().getInt(key, 0)
    }

}