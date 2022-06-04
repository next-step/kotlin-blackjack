package com.nextstep.jngcii.blackjack.view

object InputValidator {
    private const val COMMA = ","
    private const val YES = "y"
    private const val NO = "n"

    fun parseNames(input: String?): List<String>? {
        if (input == null) return null

        val names = input
            .split(COMMA)
            .map { it.trim() }

        if (names.size != 2) return null

        if (names.any { it.isBlank() }) return null

        return names
    }

    fun parseMoreable(input: String?): Boolean? {
        if (input == null) return null

        return if (input.lowercase() == YES) {
            true
        } else if (input.lowercase() == NO) {
            false
        } else null
    }
}
