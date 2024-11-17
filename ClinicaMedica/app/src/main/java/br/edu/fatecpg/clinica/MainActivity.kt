package br.edu.fatecpg.clinica

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar o Firebase Auth e Firestore
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Configurar Firestore
        db.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()

        // Teste de autenticação e acesso ao Firestore
        testeConexaoFirebase()
    }

    private fun testeConexaoFirebase() {
        // Criar um usuário anônimo para teste
        auth.signInAnonymously()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("FirebaseTest", "Autenticação anônima bem-sucedida")

                    // Teste de Firestore: adicionando um documento de teste
                    val usuarioTeste = hashMapOf(
                        "nome" to "Teste Usuario",
                        "perfil" to "Paciente"
                    )

                    db.collection("Usuarios")
                        .add(usuarioTeste)
                        .addOnSuccessListener { documentReference ->
                            Log.d("FirebaseTest", "Documento adicionado com ID: ${documentReference.id}")
                        }
                        .addOnFailureListener { e ->
                            Log.w("FirebaseTest", "Erro ao adicionar documento", e)
                        }
                } else {
                    Log.w("FirebaseTest", "Falha na autenticação anônima", task.exception)
                }
            }
    }
}
