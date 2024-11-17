package br.edu.fatecpg.clinica

data class Consulta(
    val id: String = "", // ID do documento no Firestore
    val nomePaciente: String,
    val tipoConsulta: String,
    val dataConsulta: String,
    val horaConsulta: String
)
