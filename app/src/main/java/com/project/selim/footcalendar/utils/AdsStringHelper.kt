package com.project.selim.footcalendar.utils

/**
 * AdsStringHelper
 * DAMN ® project.
 *
 * Created by Selim Abbas Said on 07/02/2018.
 * Copyright © 2016 Ad Scientiam. All rights reserved.
 */
object AdsStringHelper {
    /**
     * Concatenate multiple string using the StringBuilder class
     *
     * @param strings
     * @return a string containing all strings sent as a parameter concatenated
     */
    fun concatenateStrings(vararg strings: String): String {
        val stringBuilder = StringBuilder()
        for (currentString in strings) {
            if (currentString != null) {
                stringBuilder.append(currentString)
            }
        }
        return stringBuilder.toString()
    }

    /**
     * Capitalize a string
     *
     * @param string string to capitalize
     * @return capitalized string
     */
    fun capitalize(string: String): String {
        val build = StringBuilder()

        // Declare a character of space
        // To identify that the next character is the starting
        // of a new word
        var ch = ' '
        for (i in 0 until string.length) {

            // If previous character is space and current
            // character is not space then it shows that
            // current letter is the starting of the word
            if (ch == ' ' && string[i] != ' ')
                build.append(Character.toUpperCase(string[i]))
            else
                build.append(Character.toLowerCase(string[i]))
            ch = string[i]
        }

        return build.toString()
    }

}
