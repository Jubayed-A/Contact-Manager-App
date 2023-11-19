package com.example.contactmanagerapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.contactmanagerapp.databinding.ActivitySignInBinding
import com.example.contactmanagerapp.databinding.ActivitySignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // new intent or sign up activity open
        binding.haveAccount.setOnClickListener {
            val intentSignUp = Intent(this, SignUpActivity::class.java)
            startActivity(intentSignUp)
        }

        // sign in code here
        binding.btnSignIn.setOnClickListener {
            val userId = binding.etUserID.text.toString()
            if (userId.isNotEmpty()){
                readData(userId)
            } else{
                Toast.makeText(this, "Please enter User ID", Toast.LENGTH_SHORT).show()
            }
        }

    }

    // readData method code here
    private fun readData(userId: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(userId).get().addOnSuccessListener {
            // check user exit or not
            if (it.exists()){
                val intentMain = Intent(this, MainActivity::class.java)
                startActivity(intentMain)
                Toast.makeText(this, "Welcome to Contact Sign UP page", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "User does not exits, Please Sign Up first", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
    // readData method code end here
}