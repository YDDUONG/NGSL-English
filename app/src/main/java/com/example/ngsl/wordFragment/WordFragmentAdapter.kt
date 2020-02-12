package com.example.ngsl.wordFragment

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ngsl.R
import com.example.ngsl.room.Word
import kotlinx.android.synthetic.main.word_fragment_grad_cell.view.*

//适配器：告诉view有哪些内容，并且如何填充view
class WordFragmentAdapter : ListAdapter<Word, MyViewHolder>(DiffCallBack) {
    //比较器
    object DiffCallBack : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            //比较是不是同一个对象
            return oldItem.wordId == newItem.wordId
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            //比较内容是否相同
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //加载view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.word_fragment_grad_cell, parent, false)
        val holder = MyViewHolder(view)
        holder.itemView.apply {
            //将监听事件放在这，就不会每次加载一个item时新创建监听事件
            val onClickListener = View.OnClickListener {
                val uri = Uri.parse("https://m.youdao.com/dict?le=eng&q=${this.textViewEnglish.text}")
                Intent(Intent.ACTION_VIEW).apply {
                    this.data = uri
                    holder.itemView.context.startActivity(this)
                }
            }
            this.textViewEnglish.setOnClickListener(onClickListener)
            this.imageView.setOnClickListener(onClickListener)
        }
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //加载资源
        holder.itemView.apply {
            val item = getItem(position)
            this.textViewWordId.text = item.wordId.toString()
            this.textViewEnglish.text = item.english
        }
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
