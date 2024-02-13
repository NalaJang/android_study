package com.example.retrofit2ex.ui.presenter

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.retrofit2ex.domain.model.UserModel

// @Preview 를 사용해서 UI 미리보기를 하려면 매개변수를 사용하지 않아야 한다.
@Composable
fun UserItemView(
    userModel: UserModel,
    mainViewModel: MainViewModel = viewModel()
) {

    val id by remember { mutableIntStateOf(userModel.id) }
    var isFavorite by remember { mutableStateOf(false) }

    Card(
        // modifier : Compose 의 구성 요소들을 꾸미거나 행동을 추가하기 위한 요소들의 모임
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(16.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(userModel.avatar)
                    .crossfade(true)
                    .build()
            )
            Image(
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = "Picture of a user"
            )
            Text(
                text = userModel.name,
                modifier = Modifier.padding(start = 8.dp)
            )
            // 새로 추가한 코드
            Spacer(modifier = Modifier.weight(1f))
            FavoriteButton(
                isFavorite = isFavorite,
                onClick = {
                    isFavorite = !isFavorite
                    mainViewModel.updateFavorite(isFavorite, id)
                }
            )
        }
    }
}


@Composable
private fun FavoriteButton(isFavorite: Boolean, onClick: () -> Unit) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            imageVector =
            if( isFavorite ) Icons.Filled.Star
            else Icons.Outlined.StarOutline,

            contentDescription = "my favorite"
        )
    }
}

//@Preview
//@Composable
//fun UserItemPreview() {
//    val userModel = UserModel(0, "email", "name", "avatar")
//    MaterialTheme {
//        UserItemView(userModel)
//    }
//}