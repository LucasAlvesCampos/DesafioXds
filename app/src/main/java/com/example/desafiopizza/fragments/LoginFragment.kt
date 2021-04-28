package com.example.desafiopizza.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.desafiopizza.PizzaApi
import com.example.desafiopizza.R
import com.example.desafiopizza.databinding.FragmentLoginBinding
import com.example.desafiopizza.network.Credencials
import com.example.desafiopizza.network.LoginRetorno
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!




    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.botaoentrar.setOnClickListener {
            val email = binding.usuario.text.toString().trim()
            val password = binding.senha.text.toString().trim()
            val credencials = Credencials(email,password)
            PizzaApi.retrofitService.login(credencials).enqueue(object : Callback<LoginRetorno>{


                override fun onResponse(call: Call<LoginRetorno>, response: Response<LoginRetorno>) {

                    if (response.isSuccessful) {
                        Toast.makeText(getActivity(),"Login efetuado com sucesso", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_loginFragment_to_listaPizzasFragments)
                        }
                    else {
                        Toast.makeText(getActivity(),"Usuário ou senha Inválidos", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<LoginRetorno>, t: Throwable) {


                }

            })





        }
    }




    override fun onDestroy() {
        super.onDestroy()
        _binding = null // Liberando para o garbage colector
    }
}