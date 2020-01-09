package com.alan.cinq.model

data class ResponseCharacter(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)