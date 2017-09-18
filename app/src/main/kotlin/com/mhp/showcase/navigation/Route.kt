package com.mhp.showcase.navigation

/**
 * Routes define internal navigation targets inside the app
 */
enum class Route(val value: String) {
    /**
     * Navigate to the stream space
     */
    stream("/stream"),
    /**
     * Navigate to the article space
     */
    articleDetail("/article")
}