package com.raza.acronymsmeaning.model

data class Longform(
    val freq: Int,
    val lf: String,
    val since: Int,
    val vars: List<Variation>
)