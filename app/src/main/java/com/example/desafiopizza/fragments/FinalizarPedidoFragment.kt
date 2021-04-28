package com.example.desafiopizza.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.desafiopizza.R
import com.example.desafiopizza.databinding.FragmentFinalizarPedidoBinding


class FinalizarPedidoFragment : Fragment() {

    private var _binding: FragmentFinalizarPedidoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFinalizarPedidoBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onResume() {
        super.onResume()
        binding.botaovoltar.setOnClickListener {
            findNavController().navigate(R.id.action_finalizarPedidoFragment_to_listaPizzasFragments)
        }
    }

}