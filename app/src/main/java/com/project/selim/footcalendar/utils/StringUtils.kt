package com.project.selim.footcalendar.utils

/**
 * StringUtils
 * DAMN ® project.
 *
 * Created by Selim Abbas Said on 23/09/2018.
 * Copyright © 2016 Ad Scientiam. All rights reserved.
 */
class StringUtils {
    companion object {
        fun appendStrings(vararg strings: String): String {
            val resultString = ""

            for (s in strings) {
                resultString.plus(s)
            }
            return resultString
        }

        fun testInteroperability(value: String) = AdsStringHelper.capitalize(value)
    }
}