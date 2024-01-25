package com.example.retrofit2ex.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

// @BindingAdapter 통해 recyclerView 에 adapter 의 연결과
// ListAdapter 의 submitList 를 통해 데이터를 업데이트 할 수 있다.
@BindingAdapter("adapter", "submitList", requireAll = true)
fun bindRecyclerView(view: RecyclerView, adapter: RecyclerView.Adapter<*>, submitList: List<Any>?) {
    view.adapter = adapter.apply {
//        stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        (this as ListAdapter<Any, *>).submitList(submitList?.toMutableList())
    }
}