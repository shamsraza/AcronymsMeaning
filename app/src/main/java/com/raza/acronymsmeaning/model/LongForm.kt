package com.raza.acronymsmeaning.model

data class LongForm(
    val freq: Int,
    val lf: String,
    val since: Int,
    val vars: List<Variation>
)