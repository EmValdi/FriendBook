package org.junia.friendbook.data.interfaces

data class friend(
    var name: String = "",
    var country: String = "",
    var phone_number: String = "",
    var instagram: String = "",
    var school: String = "",
    var hobbies: List<String> = emptyList(),
    var fav_pokemon: String = "",
    var picture: String = ""
)
