package com.example.hacknosis.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hacknosis.models.Patient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PatientViewModel: ViewModel() {
    var user=MutableLiveData<Patient>()

    fun getUser(){
        val userUid = FirebaseAuth.getInstance().currentUser?.uid

// Assuming you have a reference to the Firebase Database
        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("users")

// Retrieve the user's data using their UID
        userUid?.let { uid ->
            usersRef.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // Parse the user data
                        val userData = dataSnapshot.getValue(Patient::class.java)

                        // Now you have the user data, you can use it as needed
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle errors if needed
                }
            })
        }
    }



}