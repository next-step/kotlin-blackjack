package game.blackjack.v2.domain.participant

import game.blackjack.v2.domain.card.Card
import game.blackjack.v2.domain.card.CardNumber

@JvmInline
value class HandCards(private val handCards: MutableList<Card> = mutableListOf()) {

    fun add(card: Card) = handCards.add(card)

    fun addAll(cards: List<Card>) = handCards.addAll(cards)

    fun getCurrentScore(): Int {
        val score = handCards.sumOf { it.number.getScore() }
        val aceCount = handCards.count { it.number == CardNumber.ACE }

        return reduceAceScoreIfBust(score, aceCount)
    }

    private fun reduceAceScoreIfBust(score: Int, aceCount: Int): Int {
        var adjustedScore = score
        var remainingAces = aceCount

        while (adjustedScore > 21 && remainingAces > 0) {
            adjustedScore -= 10
            remainingAces--
        }

        return adjustedScore
    }

    fun get(): List<Card> {
        return handCards.toList()
    }
}
