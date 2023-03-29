package com.brainjug.composecourse.use_case

class ValidateContactName {

    fun execute(name: String): ValidationResult {
        if (name.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The contact name can't be blank"
            )
        }
        val pattern = "^[a-zA-Z\\d\\s&@),(_'/.:-]{1,100}$".toRegex()
        if (!name.matches(pattern)) {
            return ValidationResult(
                successful = false,
                errorMessage = "Valid contact name should not contain &@),(_'/.:-"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}