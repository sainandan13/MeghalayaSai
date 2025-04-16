package org.acme.validation


import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
class VerifyAbhaValidator : ConstraintValidator<VerifyAbha, String?> {
    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        if (value.isNullOrBlank()) return true // let @NotBlank/@NotNull handle null cases
        return value.matches(Regex("^\\d{14}$"))
    }
}