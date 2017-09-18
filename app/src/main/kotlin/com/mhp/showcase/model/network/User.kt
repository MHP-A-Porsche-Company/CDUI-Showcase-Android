package com.mhp.showcase.model.network

import java.net.URI

data class User(
        val id: String,
        val name: String,
        val position: String,
        val imageUrl: URI?
)