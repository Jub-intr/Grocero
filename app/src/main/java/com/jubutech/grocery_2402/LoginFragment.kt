package com.jubutech.grocery_2402


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.jubutech.grocery_2402.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)



        binding.loginButton.setOnClickListener {

            val email = binding.emailEdt.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if (isValidEmail(email) && isValidPassword(password)) {
                LoginUser(email, password)
                findNavController().navigate(R.id.action_loginFragment_to_fragment_add_grocery)
            } else {
                Toast.makeText(
                    requireActivity(),
                    " Invalid E-mail and Password",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
        binding.createAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_sign_In_Fragment)
        }



        return binding.root
    }


    private fun LoginUser(email: String, password: String) {


        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->


            if (task.isSuccessful) {
                val user = auth.currentUser
                Toast.makeText(
                    requireActivity(),
                    "Login Successfully ${user?.email}",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(requireContext(), "${task.exception?.message}", Toast.LENGTH_SHORT)
                    .show()
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
