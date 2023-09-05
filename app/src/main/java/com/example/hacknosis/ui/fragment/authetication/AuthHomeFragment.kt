package com.example.hacknosis.ui.fragment.authetication

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hacknosis.R
import com.google.android.material.card.MaterialCardView


class AuthHomeFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        val view=inflater.inflate(R.layout.fragment_auth_home, container, false)
        val signupbtn:MaterialCardView=view.findViewById(R.id.Signup)
        val login:MaterialCardView=view.findViewById(R.id.signin)
        signupbtn.setOnClickListener {
            showRationalDialog(true)
        }
        login.setOnClickListener {
            showRationalDialog(false)
        }
        return view
    }

    private fun showRationalDialog( didsignup:Boolean) {
        val context=requireContext()
        val builder = AlertDialog.Builder(context)


        builder.setTitle("How do you wish to proceed?")
        builder.setMessage("Select a role")
        builder.setPositiveButton("Doctor") { _, _ ->
            handleRoleSelection(true,didsignup)
        }


        builder.setNegativeButton("Patient") { _, _ ->

            handleRoleSelection(false,didsignup)
        }


        val dialog = builder.create()
        dialog.show()
    }
    private fun handleRoleSelection(isDoctor: Boolean,didsignup: Boolean) {
        val navController=findNavController()
       /*if(isDoctor&&didsignup){
           navController.navigate()
       }
        else if(isDoctor==true&&didsignup==false){
           navController.navigate(R.id.action_authHomeFragment_to_loginDoctorFragment)

       }
        else if(isDoctor==false&&didsignup==true){
           navController.navigate(R.id.action_authHomeFragment_to_signupPatientFragment)
       }
        else{
           navController.navigate(R.id.action_authHomeFragment_to_loginPatientFragment)
       }*/
        val actionid= when {
            isDoctor&&didsignup->R.id.action_authHomeFragment_to_signupDoctorFragment
            isDoctor&&!didsignup->R.id.action_authHomeFragment_to_loginDoctorFragment
            !isDoctor&&!didsignup->R.id.action_authHomeFragment_to_loginPatientFragment


            else -> {
                R.id.action_authHomeFragment_to_signupPatientFragment
            }
        }
        navController.navigate(actionid)
    }



}