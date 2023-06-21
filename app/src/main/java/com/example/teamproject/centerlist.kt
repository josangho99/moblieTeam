package com.example.teamproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [centerlist.newInstance] factory method to
 * create an instance of this fragment.
 */
class centerlist : Fragment() {
    private lateinit var centerRecyclerView: RecyclerView
    private lateinit var centerAdapter: CenterListItemAdapter
    private var centerList: ArrayList<CenterListItem> = ArrayList()
    private var centerList_size = centerList.size // 센터 수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView: ViewGroup = inflater.inflate(R.layout.fragment_centerlist, container, false) as ViewGroup
        centerRecyclerView = rootView.findViewById(R.id.centerlist_items)
        centerList = CenterListItem.createContactsList(centerList_size) // 글의 갯수
        centerRecyclerView.setHasFixedSize(true)
        centerAdapter = CenterListItemAdapter(requireActivity(), centerList)
        centerRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        centerRecyclerView.adapter = centerAdapter
        // Inflate the layout for this fragment
        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment centerlist.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            centerlist().apply {
                arguments = Bundle().apply {
                }
            }
    }
}