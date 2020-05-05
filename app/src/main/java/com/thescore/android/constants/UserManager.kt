package com.thescore.android.constants

import com.thescore.android.di.TheScoreSingletonAnnotation
import javax.inject.Inject

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

@TheScoreSingletonAnnotation
class UserManager @Inject constructor(val sharedPreferenceStorage: SharedPreferenceStorage) {

    fun getBaseUrl(): String = CoreConstants.BASE_URL
}