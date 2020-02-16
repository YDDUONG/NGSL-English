package com.example.ngsl.wordFragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ngsl.R
import com.example.ngsl.room.Word
import kotlinx.android.synthetic.main.word_fragment.*

class WordFragment : Fragment() {

    private lateinit var viewModel: WordViewModel
    private lateinit var filterWords: LiveData<List<Word>>
    private lateinit var wordFragmentAdapter: WordFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //启用菜单栏
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.word_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //创建适配器
        wordFragmentAdapter = WordFragmentAdapter()
        recycleView.apply {
            adapter = wordFragmentAdapter
            layoutManager = GridLayoutManager(requireContext(), 1)
        }

        //ViewModel
        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        filterWords = viewModel.allWord
        filterWords.observe(viewLifecycleOwner, Observer {
            //为适配器提供数据
            wordFragmentAdapter.submitList(it)
        })

//        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START) {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                return false
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                //滑动之后要做的事
//                val itemPosition = viewHolder.adapterPosition //获取被删除对象的位置
//                //更新列表数据
//            }
//        }).attachToRecyclerView(recycleView)
    }

    //工具条
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
        val searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        searchView.maxWidth = 900

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val pattern = newText?.trim() as String
                Log.d("myLog", pattern)
                filterWords.removeObservers(requireActivity())
                filterWords = viewModel.searchWords(pattern)
                filterWords.observe(requireActivity(), Observer {
                    wordFragmentAdapter.submitList(it)
                })
                return true
            }
        })
    }

}
