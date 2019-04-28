package com.project.selim.footcalendar

/**
 * Event
 * DAMN ® project.
 *
 * Created by Selim Abbas Said on 19/09/2018.
 * Copyright © 2016 Ad Scientiam. All rights reserved.
 */
/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}
