package com.example.desafiopizza.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiopizza.MyAdapter
import com.example.desafiopizza.PizzaApi
import com.example.desafiopizza.databinding.FragmentListaPizzasFragmentsBinding
import com.example.desafiopizza.network.Pizza
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListaPizzasFragments : Fragment() {
    private var _binding: FragmentListaPizzasFragmentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: MyAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaPizzasFragmentsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.actionSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener, androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                myAdapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                myAdapter.filter.filter(newText)
                return true
            }

        })
    }

    override fun onResume() {
        super.onResume()
        manager = LinearLayoutManager(requireContext())
        getAllData()
    }

    fun getAllData() {
        PizzaApi.retrofitService.getAllData().enqueue(object : Callback<List<Pizza>> {
            override fun onResponse(
                    call: Call<List<Pizza>>,
                    response: Response<List<Pizza>>
            ) {
                if (response.isSuccessful) {
                    recyclerView = binding.recyclerView.apply {
                        myAdapter = MyAdapter(response.body()!!)
                        layoutManager = manager
                        adapter = myAdapter
                    }
                }
            }

            override fun onFailure(call: Call<List<Pizza>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}