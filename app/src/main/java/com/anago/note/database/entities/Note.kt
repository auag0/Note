package com.anago.note.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("content")
    val content: String,
    @ColumnInfo("last_edited_time")
    val lastEditedTime: Long
) : Serializable {
    companion object {
        fun empty(): Note {
            return Note(
                title = "",
                content = "",
                lastEditedTime = System.currentTimeMillis()
            )
        }
    }
}