package com.brainjug.composecourse.interfaces

interface AddPickupAddressEvent {
    data class SetPincode(val pincode: String): AddPickupAddressEvent
    data class SetBusinessName(val businessName: String): AddPickupAddressEvent
    data class SetNickname(val nickname: String): AddPickupAddressEvent
    data class SetAddress(val address: String): AddPickupAddressEvent
    data class SetLandmark(val landmark: String): AddPickupAddressEvent
    data class SetCity(val city: String): AddPickupAddressEvent
    data class SetState(val state: String): AddPickupAddressEvent
    data class SetContactName(val contactName: String): AddPickupAddressEvent
    data class SetMobileNumber(val mobileNumber: String): AddPickupAddressEvent
    data class SetEmailId(val emailId: String): AddPickupAddressEvent

    object Submit: AddPickupAddressEvent
}