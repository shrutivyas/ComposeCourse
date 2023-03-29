package com.brainjug.composecourse

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.brainjug.composecourse.interfaces.AddPickupAddressEvent
import com.brainjug.composecourse.viewmodel.AddPickupAddressViewModel

@Composable
fun AddPickupAddress() {

    val viewModel = viewModel<AddPickupAddressViewModel>()
    val pickupAddress = viewModel.pickupAddress
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect() { event ->
            when (event) {
                is AddPickupAddressViewModel.ValidationEvent.Success -> {
                    Toast.makeText(context, "Added address successfully", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(id = R.string.address),
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        )
        val maxChar = 6
        CustomTextField(value = pickupAddress.pincode.toString(),
            label = stringResource(id = R.string.pincode),
            onChange = {
                if (it.length <= maxChar) {
                    viewModel.onEvent(AddPickupAddressEvent.SetPincode(it))
                }
            },
            keyboardType = KeyboardType.Number,
            trailingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color.DarkGray
                    )
                }
            })
        if (pickupAddress.pincodeError != null) {
            Text(
                text = pickupAddress.pincodeError,
                color = Color.Red,
                fontSize = 12.sp
            )
        }
        CustomTextField(value = pickupAddress.businessName.toString(),
            label = stringResource(id = R.string.businessName),
            onChange = {
                viewModel.onEvent(AddPickupAddressEvent.SetBusinessName(it))
            })
        CustomTextField(value = pickupAddress.nickName.toString(),
            label = stringResource(id = R.string.nickName),
            onChange = {
                viewModel.onEvent(AddPickupAddressEvent.SetNickname(it))
            })
        CustomTextField(
            value = pickupAddress.address.toString(),
            label = stringResource(id = R.string.address),
            onChange = {
                viewModel.onEvent(AddPickupAddressEvent.SetAddress(it))
            },
            singleLine = false,
            maxLines = 3,
            imeAction = ImeAction.None
        )
        CustomTextField(value = pickupAddress.landmark.toString(),
            label = stringResource(id = R.string.landmark),
            onChange = {
                viewModel.onEvent(AddPickupAddressEvent.SetLandmark(it))
            })
        CustomTextField(value = pickupAddress.city.toString(),
            label = stringResource(id = R.string.city),
            onChange = {
                viewModel.onEvent(AddPickupAddressEvent.SetCity(it))
            })
        CustomTextField(value = pickupAddress.state.toString(),
            label = stringResource(id = R.string.state),
            onChange = {
                viewModel.onEvent(AddPickupAddressEvent.SetState(it))
            })
        CustomTextField(value = pickupAddress.contactName.toString(),
            label = stringResource(id = R.string.contactName),
            onChange = {
                viewModel.onEvent(AddPickupAddressEvent.SetContactName(it))
            })
        if (pickupAddress.contactNameError != null) {
            Text(
                text = pickupAddress.contactNameError,
                color = Color.Red,
                fontSize = 12.sp
            )
        }
        val maxMobileNoLength = 10
        CustomTextField(
            value = pickupAddress.mobileNumber.toString(),
            label = stringResource(id = R.string.mobileNumber),
            onChange = {
                if (it.length <= maxMobileNoLength) {
                    viewModel.onEvent(AddPickupAddressEvent.SetMobileNumber(it))
                }
            },
            keyboardType = KeyboardType.Phone
        )
        if (pickupAddress.mobileNumberError != null) {
            Text(
                text = pickupAddress.mobileNumberError,
                color = Color.Red,
                fontSize = 12.sp
            )
        }
        CustomTextField(
            value = pickupAddress.emailId.toString(),
            label = stringResource(id = R.string.emailId),
            onChange = {
                viewModel.onEvent(AddPickupAddressEvent.SetEmailId(it))
            },
            keyboardType = KeyboardType.Email
        )
        if (pickupAddress.emailIdError != null) {
            Text(
                text = pickupAddress.emailIdError,
                color = Color.Red,
                fontSize = 12.sp
            )
        }
        Button(
            onClick = { viewModel.onEvent(AddPickupAddressEvent.Submit) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp, 0.dp, 20.dp)
        ) {
            Text(
                text = "Add Pickup Address"
            )
        }
    }
}

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onChange: (String) -> Unit = {},
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyBoardActions: KeyboardActions = KeyboardActions(),
    isEnabled: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    isError: Boolean = false,
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp, 0.dp, 0.dp),
        value = value,
        onValueChange = onChange,
        label = {
            Text(text = label)//style = TextStyle(fontSize = 15.sp, color = Color.DarkGray)
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        textStyle = TextStyle(fontSize = 18.sp),
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        keyboardActions = keyBoardActions,
        enabled = isEnabled,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = colorResource(id = R.color.light_pink),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = singleLine,
        maxLines = maxLines,
        isError = isError
    )
}
