package com.unit_3.niaclone.ui.navigation.component

import kotlinx.serialization.Serializable

sealed class Dest {
    @Serializable
    object ForYou

    @Serializable
    object Saved

    @Serializable
    object Interests

    @Serializable
    data class InterestDetail(
        val interest : String
    )
}