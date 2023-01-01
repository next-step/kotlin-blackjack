package com.nextstep.blackjack.domain.card

import com.nextstep.blackjack.domain.card.CardNumber.ACE

data class Card(val number: CardNumber, val pattern: CardPattern) {

    fun score(): Int = number.score

    fun isAce(): Boolean = number == ACE

    override fun toString(): String {
        return number.score.toString() + pattern.pattern
    }
}
