package com.example.desafiopizza.network

import com.squareup.moshi.Json

class Credencials(
    @field:Json(name = "email") val email: String,
    @field:Json(name = "password") val password: String
)
