package com.letuse.kotlincurddataandimage.ui.Get

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.letuse.kotlincurddataandimage.R
import com.letuse.kotlincurddataandimage.ViewModel
import com.letuse.kotlincurddataandimage.model.UserModelItem
import kotlinx.android.synthetic.main.fragment_get_user.*

class GetUserFragment : Fragment() , GetUserAdapter.UserClickListener {

    lateinit var getUserAdapter : GetUserAdapter
    lateinit var viewModel : ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        getUserAdapter = GetUserAdapter()

        recyclerview_city.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            adapter = getUserAdapter
        }

        ViewModel()

        getUserAdapter.setOnClickListener(this)
    }

    private fun ViewModel(){
        viewModel.loadUsers()
        viewModel.getUsers().observe(
            viewLifecycleOwner , Observer { Users ->
                getUserAdapter.updateUsers(Users)
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //inflate menu
        inflater.inflate(R.menu.menu_main,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //handle menu item clicks
        var id : Int = item.itemId

        if(id == R.id.action_add){
            findNavController().navigate(R.id.addUserFragment)
        }else if(id == R.id.action_refresh){
            viewModel.loadUsers()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onClick(userModelItem: UserModelItem) {
        var action = GetUserFragmentDirections.actionGetUserFragmentToEditDeleteUserFragment(userModelItem.id.toInt(),userModelItem.name,userModelItem.age.toInt(),userModelItem.job)
        findNavController().navigate(action)
    }

}