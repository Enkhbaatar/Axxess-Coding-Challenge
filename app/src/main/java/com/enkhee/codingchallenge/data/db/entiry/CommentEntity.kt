package com.enkhee.codingchallenge.data.db.entiry

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="comment")
data class CommentEntry(
    val imageId:String,
    val comment:String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}