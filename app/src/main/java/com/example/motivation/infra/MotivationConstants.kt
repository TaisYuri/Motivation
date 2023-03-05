package com.example.motivation.infra

import com.example.motivation.R
import android.content.Context

class MotivationConstants private constructor(){
    object KEY {
        const val USER_NAME = "USER_NAME"
    }

    object FILTER {
        const val ALL_INCLUSIVE = R.string.all_inclusive
        const val HAPPY = R.string.happy
        const val SUNNY = R.string.sunny
    }

    object LANGUAGE {
        const val PORTUGUESE = "pt"
        const val ENGLISH = "en"
    }

}