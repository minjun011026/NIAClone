package com.unit_3.niaclone.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val firestore: FirebaseFirestore
){

}