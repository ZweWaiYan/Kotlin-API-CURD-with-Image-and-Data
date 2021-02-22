package com.letuse.kotlincurddataandimage.ui.Add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.letuse.kotlincurddataandimage.R
import com.letuse.kotlincurddataandimage.ViewModel
import com.letuse.kotlincurddataandimage.api.ApiClient
import com.letuse.kotlincurddataandimage.model.UserModelItem
import kotlinx.android.synthetic.main.fragment_add_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddUserFragment : Fragment() {

    lateinit var Name : String
    lateinit var Age : String
    lateinit var Job : String
    lateinit var viewModel : ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        btn_add_user.setOnClickListener {
            Name = edt_add_name.text.toString()
            Age = edt_add_age.text.toString()
            Job = edt_add_job.text.toString()

            AddUser(Name , Age , Job)

            Toast.makeText(context , "Successfully Send to API", Toast.LENGTH_SHORT).show()
            viewModel.loadUsers()
        }

    }

    fun AddUser(Name : String , Age : String , Job : String){
        var apiClient = ApiClient()
        var call = apiClient.addUsers(Name,Age,Job)
        call.enqueue(object : Callback<List<UserModelItem>> {
            override fun onResponse(call: Call<List<UserModelItem>>, response: Response<List<UserModelItem>>) {
            }

            override fun onFailure(call: Call<List<UserModelItem>>, t: Throwable) {
            }

        })
    }
}