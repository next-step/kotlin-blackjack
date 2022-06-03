package com.nextstep.jngcii.blackjack.domain

class PlayerBoard {
    private val _cards = mutableListOf<Card>()
    val cards get() = _cards.toList()
}
