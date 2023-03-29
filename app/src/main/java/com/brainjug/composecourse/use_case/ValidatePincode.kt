package com.brainjug.composecourse.use_case

import android.util.Patterns

class ValidatePincode {

    fun execute(pin: String): ValidationResult {
        if (pin.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The pincode can't be blank"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}