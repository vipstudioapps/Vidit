package com.vidit.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_videos")
data class ContactVideo(
    @PrimaryKey
    val contactId: String,
    val videoUri: String,
    val contactName: String = "",
    val createdAt: Long = System.currentTimeMillis()
)