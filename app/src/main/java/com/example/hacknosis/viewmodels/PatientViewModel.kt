package com.example.hacknosis.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
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

    fun getUser() {
        val userUid = FirebaseAuth.getInstance().currentUser
        var email:String?=null
        email=userUid?.email
        if(email!=null) {
            val database = FirebaseDatabase.getInstance()
            database.getReference(encodeEmail(email))
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        var patient = dataSnapshot.getValue(Patient::class.java)
                        if(patient!=null){
                            user.value=patient!!
                        }

                    }
                    override fun onCancelled(error: DatabaseError) {
                        Log.w(TAG, "Failed to read value.", error.toException())
                    }
                })
        }
    }
    private fun encodeEmail(email: String): String{
        return email.replace(".", ",")
    }

    private fun decodeEmail(encodedEmail: String): String {
        return encodedEmail.replace(",", ".")
    }



}