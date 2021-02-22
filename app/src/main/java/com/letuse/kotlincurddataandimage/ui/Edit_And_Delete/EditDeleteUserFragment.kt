package com.letuse.kotlincurddataandimage.ui.Edit_And_Delete

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
import kotlinx.android.synthetic.main.fragment_edit_delete_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditDeleteUserFragment : Fragment() {

    lateinit var viewModel : ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_delete_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        var DetailUser = arguments?.let { EditDeleteUserFragmentArgs.fromBundle(it) }
        var ID : Int = DetailUser!!.id
        var Name : String = DetailUser!!.name
        var Job : String = DetailUser!!.job
        var Age : Int = DetailUser!!.age

        txt_id_detail.setText(ID.toString())
        txt_name_detail.setText(Name)
        txt_age_detail.setText(Age.toString())
        txt_job_detail.setText(Job)

        btn_delete.setOnClickListener {
            var apiClient = ApiClient()
            var call = apiClient.deleteUsers(ID.toString())
            call.enqueue(object : Callback<List<UserModelItem>>{
                override fun onFailure(call: Call<List<UserModelItem>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<List<UserModelItem>>,
                    response: Response<List<UserModelItem>>
                ) {
                }
            })

            Toast.makeText(context , "Successfully Delete", Toast.LENGTH_SHORT).show()
            viewModel.loadUsers()
        }

        btn_edit.setOnClickListener {

            var edited_Name = txt_name_detail.text.toString()
            var edited_Age = txt_age_detail.text.toString()
            var edited_Job = txt_job_detail.text.toString()

            var apiClient = ApiClient()
            var call = apiClient.editUsers(ID.toString(),edited_Name,edited_Age,edited_Job)
            call.enqueue(object : Callback<List<UserModelItem>>{
                override fun onFailure(call: Call<List<UserModelItem>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<List<UserModelItem>>,
                    response: Response<List<UserModelItem>>
                ) {
                }
            })

            Toast.makeText(context , "Successfully Updated", Toast.LENGTH_SHORT).show()
            viewModel.loadUsers()
        }
    }

}