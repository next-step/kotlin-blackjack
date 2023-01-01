package com.nextstep.blackjack.domain.card

data class Card(val number: CardNumber, val pattern: CardPattern) {

    override fun toString(): String {
        return number.score.toString() + pattern.pattern
    }
}
