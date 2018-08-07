package com.mhp.showcase.navigation

/**
 * Routes define internal navigation targets inside the app
 */
enum class Route(val value: String) {
    /**
     * Navigate to the stream space
     */
    STREAM("/stream"),
    /**
     * Navigate to the article space
     */
    ARTICLE("/article")
}