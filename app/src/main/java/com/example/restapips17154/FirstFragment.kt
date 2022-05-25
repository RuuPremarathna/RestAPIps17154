package com.example.restapips17154

import Adapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restapips17154.api.Post
import com.example.restapips17154.api.RetrifitAPI
import com.example.restapips17154.databinding.FragmentFirstBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting the recyclerview by its id
        val recyclerview = binding.recyclerview

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this.context)




        val apiInterface = RetrifitAPI.create().getPosts()

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue( object : Callback<List<Post>> {
            @SuppressLint("CheckResult")
            override fun onResponse(call: Call<List<Post>>?, response: Response<List<Post>>?) {

                if(response?.body() != null){
                    // This will pass the ArrayList to our Adapter
                    val adapter = Adapter(response.body()!!)

                    // Setting the Adapter with the recyclerview
                    recyclerview.adapter = adapter




                    adapter.clickEvent.subscribe {
                        val clickedPostId = it.toInt()
//                        Toast.makeText(parentFragment?.context , "clicked $clickedPostId", Toast.LENGTH_SHORT).show()

                        response.body()!!.forEach {
                            if(clickedPostId == it.id) {
                                setFragmentResult("requestKey",
                                    bundleOf(
                                    "userId" to it.userId,
                                    "postid" to it.id,
                                    "title" to it.title,
                                    "body" to it.body
                                        ))
                                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                            }
                        }

                    }




                }
                //    Adapter.setMoviesListItems(response.body()!!)
            }

            override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {
                Log.i("FirstFragment","Failed to retrieve")
            }
        })

        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}