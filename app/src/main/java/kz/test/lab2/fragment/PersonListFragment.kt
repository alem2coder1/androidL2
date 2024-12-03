package kz.test.lab2.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import kz.test.lab2.R
import kz.test.lab2.adapter.PersonListAdapter
import kz.test.lab2.databinding.FragmentPersonListBinding
import kz.test.lab2.model.entity.Person
import kz.test.lab2.model.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PersonListFragment : Fragment() {

    companion object {
        fun newInstance() = PersonListFragment()
    }

    private var _binding: FragmentPersonListBinding? = null
    private val binding
        get() = _binding!!

    private val adapter: PersonListAdapter by lazy {
        PersonListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonListBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun fetchPersonList(name: String) {
        ApiClient.getPersonList(name).enqueue(object : Callback<List<Person>> {
            override fun onResponse(call: Call<List<Person>>, response: Response<List<Person>>) {
                if (response.isSuccessful) {
                    val personList = response.body() ?: emptyList()
                    if (personList.isNotEmpty()) {
                        adapter.setItems(personList)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.no_results, name),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.error_response, response.code().toString()),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Person>>, t: Throwable) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.error_response, t.message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun setupUI() {
        with(binding) {
            personList.adapter = adapter

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    if (query.isNotEmpty()) {
                        fetchPersonList(query)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            R.string.enter_name_error,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                    return false
                }

            })

//            submitButton.setOnClickListener {
//                val name = binding.nameInput.text.toString().trim()
//                if (name.isNotEmpty()) {
//                    fetchPersonList(name)
//                } else {
//                    Toast.makeText(requireContext(), R.string.enter_name_error, Toast.LENGTH_SHORT)
//                        .show()
//                }
//            }
        }
    }
}