package com.mhp.showcase.block.user

import com.mhp.showcase.block.BaseBlock
import com.mhp.showcase.model.network.User

data class UserBlock(override val id: String,
                     val user: User) : BaseBlock