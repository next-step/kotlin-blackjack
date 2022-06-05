package blackjack.domain

import kotlin.math.abs

data class Participant(
    val name: String,
    private val cardDeck: Deck
) {
    private val _playerCards = mutableListOf<Card>()

    val cards: List<Card>
        get() = _playerCards.toList()

    fun score(): Int {
        val score = cards.sumOf { it.denomination.point }
        return if (cards.hasAceCard()) {
            compareAbsoluteValue(score)
        } else score
    }

    private fun compareAbsoluteValue(score: Int): Int {
        val scoreContainBigValueOfAce = score - Card.Denomination.ACE.point + BIG_VALUE_OF_ACE
        return if (abs(BLACK_JACK - scoreContainBigValueOfAce) > abs(BLACK_JACK - score)) {
            score
        } else scoreContainBigValueOfAce
    }

    fun addCard() {
        _playerCards.add(cardDeck.draw())
    }

    private fun List<Card>.hasAceCard(): Boolean {
        return this.map { it.denomination }.contains(Card.Denomination.ACE)
    }

    companion object {
        fun of(playerName: String, cardDeck: Deck): Participant {
            return Participant(playerName, cardDeck = cardDeck)
        }

        const val BLACK_JACK = 21
        const val BIG_VALUE_OF_ACE = 11
    }
}
