package com.example.hacknosis.ui.fragment.authetication

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hacknosis.databinding.FragmentSignupPatientBinding
import com.example.hacknosis.models.Patient
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupPatientFragment : Fragment() {
    private var _binding:FragmentSignupPatientBinding?=null
    private val binding get()=_binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding=FragmentSignupPatientBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.submit.setOnClickListener{
            RegisterUser()
        }

        return view
    }
    private fun RegisterUser(){
        val name=binding.nameEditText.text.toString()
        val email=binding.emailEditText.text.toString().trim()
        val password=binding.passwordEditText.text.toString().trim()
        val phone=binding.phoneEditText.text.toString().trim()
        if(validateForm(name,email,password,phone)){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener { task->
                    if(task.isSuccessful){
                        var patientinit=Patient(name,email,phone)
                        val database: DatabaseReference = FirebaseDatabase.getInstance().reference
                        //database.child(encodeEmail(email)).setValue(patientinit)
                        database.child("Users").child(encodeEmail(email)).setValue(patientinit) { databaseError, databaseReference ->
                            if (databaseError != null) {
                                // There was an error while uploading data
                                Toast.makeText(
                                    requireContext(),
                                    "Data upload failed: " + databaseError.message,
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                // Data was uploaded successfully
                                Toast.makeText(
                                    requireContext(),
                                    "Data uploaded successfully",
                                    Toast.LENGTH_LONG
                                ).show()
                                //FirebaseAuth.getInstance().signOut()
                                //requireActivity().finish()
                            }
                        }

                    }

                    else{
                        Toast.makeText(requireActivity(), "not successful: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
        }
        else{
            Toast.makeText(requireActivity(), "you cannot register", Toast.LENGTH_SHORT).show()
        }

    }
    private fun validateForm(name:String,email:String,password:String,phone:String): Boolean {
        return when {
            TextUtils.isEmpty(name)->{
                showErrorSnackBar("Please enter your name")
                false
            }
            TextUtils.isEmpty(password)->{
                showErrorSnackBar("Please enter a valid password")
                false
            }
            TextUtils.isEmpty(email)->{
                showErrorSnackBar("Please enter an email")
                false
            }
            TextUtils.isEmpty(phone)||phone.length!=10->{
                showErrorSnackBar("Please Enter a valid 10 digit number")
                false
            }

            else -> {true}
        }
    }
    private fun showErrorSnackBar(message: String) {
        val snackBar = Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
        snackBar.show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun encodeEmail(email: String): String {
        return email.replace(".", ",")
    }

    private fun decodeEmail(encodedEmail: String): String {
        return encodedEmail.replace(",", ".")
    }


}