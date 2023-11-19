package com.example.contactmanagerapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.contactmanagerapp.databinding.ActivityMainBinding
import com.example.contactmanagerapp.databinding.ActivitySignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var database : DatabaseReference

    companion object{
        const val KEY1 = "com.example.contactmanagerapp.name"
        const val KEY2 = "com.example.contactmanagerapp.mail"
        const val KEY3 = "com.example.contactmanagerapp.phone"
        const val KEY4 = "com.example.contactmanagerapp.userId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Change status bar color programmatically in an Activity
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.yellow)
        }

        // new intent or all contact list activity show
        binding.showContact.setOnClickListener {
            
            Toast.makeText(this, "this page is under maintains, Coming Soon", Toast.LENGTH_SHORT).show()

            // for showing data in contact list with button
            val showList = binding.showContact.text.toString()
            if (showList.isNullOrEmpty()){
                showData(showList)
            } else{
//                Toast.makeText(this, "Nothing!", Toast.LENGTH_SHORT).show()
                showData(showList)
            }

        }

        // signUp code here
        binding.btnSignUp.setOnClickListener {
            val name = binding.etName.text.toString()
            val mail = binding.etEmail.text.toString()
            val userId = binding.etUserID.text.toString()
            val phone = binding.etPhone.text.toString()

            if (name.isNotEmpty() && mail.isNotEmpty() && userId.isNotEmpty() && phone.isNotEmpty()){
                val user = contactData(name,mail,userId,phone)
                database = FirebaseDatabase.getInstance().getReference("Contacts")
                database.child(userId).setValue(user).addOnSuccessListener {
                    binding.etName.text?.clear()
                    binding.etEmail.text?.clear()
                    binding.etPhone.text?.clear()
                    binding.etUserID.text?.clear()
                    Toast.makeText(this, "User Contact Registered Successfully",Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "User Contact Registered Failed",Toast.LENGTH_SHORT).show()
                }
            } else{
                Toast.makeText(this, "Please Fill all The Field",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun showData(showList: String) {

        val name = binding.etName.text.toString()
        val mail = binding.etEmail.text.toString()
        val userId = binding.etUserID.text.toString()
        val phone = binding.etPhone.text.toString()
        
        database = FirebaseDatabase.getInstance().getReference("Contacts")
        database.child(showList).get().addOnSuccessListener {
            if (it.exists()){
                // welcome user to new intent
                val nameShow = it.child("name").value
                val mailShow = it.child("mail").value
                val phoneShow = it.child("phone").value
                val userIdShow = it.child("userId").value
                // new intent open
                val intentContact = Intent(this, ContactListActivity::class.java)
                intentContact.putExtra(KEY1, name.toString())
                intentContact.putExtra(KEY2, mail.toString())
                intentContact.putExtra(KEY3, phone.toString())
                intentContact.putExtra(KEY4, userId.toString())
                startActivity(intentContact)
            }else{
//                Toast.makeText(this, "User does not exit, Please sign up first", Toast.LENGTH_SHORT).show()

            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}