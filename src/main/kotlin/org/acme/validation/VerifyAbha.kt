package org.acme.validation


import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [VerifyAbhaValidator::class])
annotation class VerifyAbha(
    val message: String = "Invalid ABHA ID. It must be a 14-digit number.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)