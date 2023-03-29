package com.brainjug.composecourse.data

data class PickupAddress(
    val pincode: String = "",
    val pincodeError: String? = null,
    val businessName: String = "",
    val businessNameError: String? = null,
    val nickName: String = "",
    val nickNameError: String? = null,
    val address: String = "",
    val addressError: String? = null,
    val landmark: String = "",
    val landmarkError: String? = null,
    val city: String = "",
    val cityError: String? = null,
    val state: String = "",
    val stateError: String? = null,
    val contactName: String = "",
    val contactNameError: String? = null,
    val mobileNumber: String = "",
    val mobileNumberError: String? = null,
    val emailId: String = "",
    val emailIdError: String? = null,
)
