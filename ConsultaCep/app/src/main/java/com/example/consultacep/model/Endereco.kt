package com.exemplo.consultacep.model

import java.io.Serializable

data class Endereco(
    val logradouro: String?,
    val bairro: String?,
    val localidade: String?,
    val uf: String?
) : Serializable
