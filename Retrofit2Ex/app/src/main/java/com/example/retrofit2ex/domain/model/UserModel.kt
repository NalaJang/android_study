package com.example.retrofit2ex.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
//data class UserModel(
//    val id: Int,
//    val email: String,
//    val name: String,
//    val avatar: String
//)

class UserModel() {

    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("email")
    var email: String = ""

    @SerializedName("name")
    var name: String = ""

    @SerializedName("avatar")
    var avatar: String = ""
}
