package com.example.contactmanagerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contactmanagerapp.databinding.ActivityContactListBinding

class ContactListActivity : AppCompatActivity() {
    lateinit var binding: ActivityContactListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(MainActivity.KEY1)
        val mail = intent.getStringExtra(MainActivity.KEY2)
        val phone = intent.getStringExtra(MainActivity.KEY3)
        val userId = intent.getStringExtra(MainActivity.KEY4)

        binding.textView.text = "Name : $name"
        binding.txtName.text = "Name : $name"
        binding.txtMail.text = "Mail : $mail"
        binding.txtPhone.text = "Phone : $phone"
        binding.txtUserId.text = "User ID : $userId"


    }
}