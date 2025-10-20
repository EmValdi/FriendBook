package org.junia.friendbook.ui

import org.junia.friendbook.data.Testdata

object Strings {
    val emailLabel = "Email"
    val confirmEmailLabel = "Confirm Email"
    val passwordLabel = "Password"

    val blankEmailError = "Please provide an email address"
    val blankConfirmEmailError = "Please confirm your email address"
    val blankPasswordError = "Please provide a password"
    val invalidPasswordError = "Password must be at least 6 characters long"
    val invalidEmailError = "Please provide a valid email address"
    val wrongConfirmEmailError = "Email address does not match"

    val friendlistTop = "Your Friends"
    val back_button = "Back"
    val friendAmount = "${Testdata().loadData().size} friends"
}