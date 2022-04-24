package com.amirovdev.loovie.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Film object
 *
 * the id of image in res folder:
 * val poster: Int
 *
 * Inheritance from Parcelable and the @Parcelize annotation
 * allow passing the Film object to other Activities
 */

@Parcelize
data class Film(
    val id: Int,
    val title: String?,
    val poster: Int,
    val desc: String?,
    var isInFavorites: Boolean = false
) : Parcelable