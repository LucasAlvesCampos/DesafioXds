package com.example.desafiopizza.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.desafiopizza.fragments.DetalhesPizzaFragmentArgs
import com.example.desafiopizza.R
import com.example.desafiopizza.databinding.FragmentDetalhesPizzaBinding


class DetalhesPizzaFragment : Fragment() {
    private var _binding: FragmentDetalhesPizzaBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetalhesPizzaFragmentArgs>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetalhesPizzaBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.detalhesPizza
        binding.nomepizza.text = "Pizza de ${args.detalhesPizza.name}"
        binding.ratingBar2.rating = args.detalhesPizza.rating.toFloat()
        val precoFormatadoM = "%.2f".format(args.detalhesPizza.priceM)
        binding.preco.text = "R$ ${precoFormatadoM}".replace(".",",")
        Glide.with(view.context).load(args.detalhesPizza.imageUrl).centerCrop().into(binding.fotodetalhe)}

    override fun onResume() {
        super.onResume()
        binding.botaoP.setOnClickListener {
            val precoFormatadoP = "%.2f".format(args.detalhesPizza.priceP)
            binding.botaoM.setBackgroundColor(Color.WHITE)
            binding.botaoG.setBackgroundColor(Color.WHITE)
            binding.botaoP.setBackgroundColor(4286236239.toInt())
            binding.preco.text = "R$ ${precoFormatadoP}".replace(".",",")

        }

        binding.botaoM.setOnClickListener {
            val precoFormatadoM = "%.2f".format(args.detalhesPizza.priceM)
            binding.botaoP.setBackgroundColor(Color.WHITE)
            binding.botaoG.setBackgroundColor(Color.WHITE)
            binding.botaoM.setBackgroundColor(4286236239.toInt())
            binding.preco.text = "R$ ${precoFormatadoM}".replace(".",",")
        }

        binding.botaoG.setOnClickListener {
            val precoFormatadoG = "%.2f".format(args.detalhesPizza.priceG)
            binding.botaoP.setBackgroundColor(Color.WHITE)
            binding.botaoM.setBackgroundColor(Color.WHITE)
            binding.botaoG.setBackgroundColor(4286236239.toInt())
            binding.preco.text = "R$ ${precoFormatadoG}".replace(".",",")
        }

        binding.botaocomprar.setOnClickListener {
            findNavController().navigate(R.id.action_detalhesPizzaFragment_to_finalizarPedidoFragment)
        }
    }
}