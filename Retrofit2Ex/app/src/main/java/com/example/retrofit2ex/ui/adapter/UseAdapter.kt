package com.example.retrofit2ex.ui.adapter

import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit2ex.domain.model.UserModel
import com.example.retrofit2ex.ui.UserItemView
import com.google.android.material.composethemeadapter.MdcTheme

class UseAdapter: ListAdapter<UserModel, RecyclerView.ViewHolder>(UserModelDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(ComposeView(parent.context))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = getItem(position)
        (holder as UserViewHolder).bind(user)
    }

    // viewHolder 를 사용하면, 한 번 생산하여 저장했던 뷰 다시 findViewById() 를 통해 뷰를 불러올 필요가 사라진다.
    inner class UserViewHolder(composeView: ComposeView): RecyclerView.ViewHolder(composeView) {
        fun bind(userModel: UserModel) {
            ( itemView as ComposeView ).setContent {
                // MdcTheme 함수는  MDC 테마를 자동으로 읽고 사용자를 대신하여 밝은 테마와 어두운 테마를 위해 Material Theme 로 전달한다.
                MdcTheme {
                    UserItemView(userModel = userModel)
                }
            }
        }
    }
}

// DiffUtil 은 어댑터에서 현재 데이터 리스트와 교체될 데이터 리스트를 비교하여 무엇이 바뀌었는 지 알아내는 클래스이다.
// 이를 통해 아이템에 수정이 생겼을 때, 전체 리스트를 갱신하는 것이 아니라 바뀐 아이템에 대해서만 데이터를 바꿔주기 때문에 효율적인 데이터 갱신이 이루어진다.
private class UserModelDiffCallback: DiffUtil.ItemCallback<UserModel>() {
    override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem.id == newItem.id
    }

    // areItemsTheSame() 가 먼저 실행이 되고 결과가 true 로 반환되었을 경우에 areContentsTheSame() 이 호출된다.
    // 그렇기 때문에 areItemsTheSame() 에서는 id 처럼 아이템을 식별할 수 있 유니크한 값을 비교하고,
    // areContentsTheSame() 에서는 아이템의 내부 정보가 동일한 지 비교한다.
    override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem == newItem
    }

}