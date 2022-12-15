package com.nextstep.blackjack.domain

import com.nextstep.blackjack.domain.BlackJackConstants.BLACKJACK_ACE_CORRECTION
import com.nextstep.blackjack.domain.BlackJackConstants.BLACKJACK_ACE_JUDGE_THRESHOLD
import com.nextstep.blackjack.domain.BlackJackConstants.BLACKJACK_NUMBER

data class Player(val name: String) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() {
            return _cards.toList()
        }

    fun draw(drawCards: List<Card>) {
        _cards.addAll(drawCards)
    }

    fun isBust(): Boolean {
        return calculateScore() > BLACKJACK_NUMBER
    }

    fun calculateScore(): Int {
        val scoreBeforeAceCorrection = _cards.sumOf { it.getNumber() }
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
