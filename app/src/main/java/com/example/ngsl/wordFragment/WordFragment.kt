package com.example.ngsl.wordFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ngsl.R
import kotlinx.android.synthetic.main.word_fragment.*

class WordFragment : Fragment() {

    private lateinit var viewModel: WordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.word_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //创建适配器
        val wordFragmentAdapter = WordFragmentAdapter()
        recycleView.apply {
            adapter = wordFragmentAdapter
            layoutManager = GridLayoutManager(requireContext(), 1)
        }

        //ViewModel
        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        viewModel.allWord.observe(viewLifecycleOwner, Observer {
            //为适配器提供数据
            wordFragmentAdapter.submitList(it)
        })
    }

}
