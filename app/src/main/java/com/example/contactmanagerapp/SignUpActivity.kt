package com.example.contactmanagerapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.contactmanagerapp.databinding.ActivitySignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.ref.Reference

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // new intent or sign in activity open
        binding.haveAccount.setOnClickListener {
            val intentSignIn = Intent(this, SignInActivity::class.java)
            startActivity(intentSignIn)
        }

        // signUp button code here
        binding.btnSignUp.setOnClickListener {
            val name = binding.etName.text.toString()
            val mail = binding.etEmail.text.toString()
            val userId = binding.etUserID.text.toString()
            val password = binding.etPassword.text.toString()

            if (name.isNotEmpty() && mail.isNotEmpty() && userId.isNotEmpty() && password.isNotEmpty()){
                val user = User(name,mail,userId,password)
                database = FirebaseDatabase.getInstance().getReference("Users")
                database.child(userId).setValue(user).addOnSuccessListener {
                    binding.etName.text?.clear()
                    binding.etEmail.text?.clear()
                    binding.etPassword.text?.clear()
                    binding.etUserID.text?.clear()
                    Toast.makeText(this, "User Register Successfully", Toast.LENGTH_SHORT).show()
                } .addOnFailureListener {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
            } else{
                Toast.makeText(this, "Please Fill all The Field",Toast.LENGTH_SHORT).show()
            }
        }

    }
}