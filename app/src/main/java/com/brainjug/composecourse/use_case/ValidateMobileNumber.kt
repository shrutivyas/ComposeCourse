package com.brainjug.composecourse.use_case

class ValidateMobileNumber {

    fun execute(mobile: String): ValidationResult {
        if (mobile.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The mobile number can't be blank"
            )
        }
        val pattern = "^[6-9]\\d{9}$".toRegex()
        if (!mobile.matches(pattern)) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please enter a valid mobile number"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}