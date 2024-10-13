package com.jubutech.grocery_2402

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.jubutech.grocery_2402.databinding.FragmentLoginBinding
import com.jubutech.grocery_2402.databinding.FragmentSignInBinding


class Sign_In_Fragment : Fragment() {


    lateinit var binding: FragmentSignInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)




        binding.signinButton.setOnClickListener{

            val username = binding.Useredt.text.toString().trim()
            val email = binding.emailEdt.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if (isValidEmail(email) && isValidPassword(password)){

                signin(username, email, password)
            }else {
                Toast.makeText(requireActivity(), "Invalid E-Mail and Password", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.AlreadyHavingAccount.setOnClickListener {

            findNavController().navigate(R.id.action_sign_In_Fragment_to_loginFragment)
        }
        return binding.root
    }
    private fun signin(username: String, email: String, password: String) {

        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task->

            if(task.isSuccessful){
                Toast.makeText(requireActivity(), "Create Account Successfully", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_sign_In_Fragment_to_fragment_add_grocery)

            }else{
                Toast.makeText(requireActivity(), "Ki shomossa janina", Toast.LENGTH_SHORT).show()
            }

        }

    }


    fun isValidEmail(email: String): Boolean {

        return Patterns.EMAIL_ADDRESS.matcher(email).matches()

    }

    fun isValidPassword(password: String): Boolean {

        val passRegex = Regex("^.{8}\$")

        return password.matches(passRegex)

    }


}
