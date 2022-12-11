package com.nextstep.blackjack.domain

import com.nextstep.blackjack.domain.Card.Companion.BLACKJACK_ACE_CORRECTION
import com.nextstep.blackjack.domain.Card.Companion.BLACKJACK_ACE_JUDGE_THRESHOLD
import com.nextstep.blackjack.domain.Card.Companion.BLACKJACK_NUMBER

data class Player(val name: String) {
    private var _cards: List<Card> = mutableListOf()

    fun draw(drawCards: List<Card>) {
        _cards = _cards.plus(drawCards)
    }

    fun isBust(): Boolean {
        return calculateScore() > BLACKJACK_NUMBER
    }

    fun getCards(): List<Card> {
        return _cards.toList()
    }

    fun calculateScore(): Int {
        val scoreBeforeAceCorrection = _cards.sumOf { it.cardNumber.number }
        if (!containsAce()) {
            return scoreBeforeAceCorrection
        }

        return correctAces(scoreBeforeAceCorrection)
    }

    private fun correctAces(scoreBeforeAceCorrection: Int): Int {
        var scoreAfterAceCorrection = scoreBeforeAceCorrection
        val aceCounts = _cards.count { it.cardNumber == CardNumber.ACE }
        repeat(aceCounts) {
            if (scoreAfterAceCorrection <= BLACKJACK_ACE_JUDGE_THRESHOLD) {
                scoreAfterAceCorrection += BLACKJACK_ACE_CORRECTION
            }
        }
        return scoreAfterAceCorrection
    }

    private fun containsAce() = _cards.any { it.cardNumber == CardNumber.ACE }
}
