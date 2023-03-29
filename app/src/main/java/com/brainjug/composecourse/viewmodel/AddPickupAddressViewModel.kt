package com.brainjug.composecourse.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brainjug.composecourse.data.PickupAddress
import com.brainjug.composecourse.interfaces.AddPickupAddressEvent
import com.brainjug.composecourse.use_case.ValidateContactName
import com.brainjug.composecourse.use_case.ValidateEmail
import com.brainjug.composecourse.use_case.ValidateMobileNumber
import com.brainjug.composecourse.use_case.ValidatePincode
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AddPickupAddressViewModel(
    private val validatePincode: ValidatePincode = ValidatePincode(),
    private val validateContactName: ValidateContactName = ValidateContactName(),
    private val validateMobileNumber: ValidateMobileNumber = ValidateMobileNumber(),
    private val validateEmail: ValidateEmail = ValidateEmail(),
) : ViewModel() {

    var pickupAddress by mutableStateOf(PickupAddress())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(pickupAddressEvent: AddPickupAddressEvent) {
        when (pickupAddressEvent) {
            is AddPickupAddressEvent.SetPincode -> {
                pickupAddress =
                    pickupAddress.copy(
                        pincode = pickupAddressEvent.pincode
                    )
                validatePincode()
            }
            is AddPickupAddressEvent.SetBusinessName -> {
                pickupAddress =
                    pickupAddress.copy(
                        businessName = pickupAddressEvent.businessName
                    )
            }
            is AddPickupAddressEvent.SetNickname -> {
                pickupAddress =
                    pickupAddress.copy(
                        nickName = pickupAddressEvent.nickname
                    )
            }
            is AddPickupAddressEvent.SetAddress -> {
                pickupAddress =
                    pickupAddress.copy(
                        address = pickupAddressEvent.address
                    )
            }
            is AddPickupAddressEvent.SetLandmark -> {
                pickupAddress =
                    pickupAddress.copy(
                        landmark = pickupAddressEvent.landmark
                    )
            }
            is AddPickupAddressEvent.SetCity -> {
                pickupAddress =
                    pickupAddress.copy(
                        city = pickupAddressEvent.city
                    )
            }
            is AddPickupAddressEvent.SetState -> {
                pickupAddress =
                    pickupAddress.copy(
                        state = pickupAddressEvent.state
                    )
            }
            is AddPickupAddressEvent.SetContactName -> {
                pickupAddress =
                    pickupAddress.copy(
                        contactName = pickupAddressEvent.contactName
                    )
            }
            is AddPickupAddressEvent.SetMobileNumber -> {
                pickupAddress =
                    pickupAddress.copy(
                        mobileNumber = pickupAddressEvent.mobileNumber
                    )

                validateMobileNumber()
            }
            is AddPickupAddressEvent.SetEmailId -> {
                pickupAddress =
                    pickupAddress.copy(
                        emailId = pickupAddressEvent.emailId
                    )
            }
            is AddPickupAddressEvent.Submit -> {
                Log.e("AddPickupAddress", "## Submit $pickupAddress")
                submitData()
            }
        }
    }

    private fun validatePincode(){
        val pincodeResult = validatePincode.execute(pickupAddress.pincode)

        pickupAddress = if(!pincodeResult.successful){
            pickupAddress.copy(
                pincodeError = pincodeResult.errorMessage
            )
        }else{
            pickupAddress.copy(
                pincodeError = null
            )
        }
    }

    private fun validateMobileNumber(){
        val mobileNumberResult = validateMobileNumber.execute(pickupAddress.mobileNumber)

        pickupAddress = if(!mobileNumberResult.successful){
            pickupAddress.copy(
                mobileNumberError = mobileNumberResult.errorMessage
            )
        }else{
            pickupAddress.copy(
                mobileNumberError = null
            )
        }
    }

    private fun submitData() {
        val pincodeResult = validatePincode.execute(pickupAddress.pincode)
        val emailResult = validateEmail.execute(pickupAddress.emailId)
        val contactNameResult = validateContactName.execute(pickupAddress.contactName)
        val mobileNumberResult = validateMobileNumber.execute(pickupAddress.mobileNumber)

        val hasError = listOf(
            pincodeResult,
            emailResult,
            contactNameResult,
            mobileNumberResult
        ).any { !it.successful }

        if (hasError) {
            pickupAddress = pickupAddress.copy(
                pincodeError = pincodeResult.errorMessage,
                emailIdError = emailResult.errorMessage,
                contactNameError = contactNameResult.errorMessage,
                mobileNumberError = mobileNumberResult.errorMessage
            )
            return
        }else{
            pickupAddress = pickupAddress.copy(
                pincodeError = null,
                emailIdError = null,
                contactNameError = null,
                mobileNumberError = null
            )
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }
}