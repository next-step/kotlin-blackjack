package com.nextstep.jngcii.blackjack.view

object InputValidator {
    private const val COMMA = ","

    fun parseNames(input: String?): List<String>? {
        if (input == null) return null

        val names = input
            .split(COMMA)
            .map { it.trim() }

        if (names.size != 2) return null

        if (names.any { it.isBlank() }) return null

        return names
    }
}
