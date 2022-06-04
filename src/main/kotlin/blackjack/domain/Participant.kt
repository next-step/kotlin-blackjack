package blackjack.domain

import kotlin.math.abs

data class Participant(
    val name: String,
    private val playerCards: List<Card> = emptyList(),
    private val cardDeck: Deck
) {
    private val _playerCards = ArrayList<Card>()

    val cards: List<Card>
        get() = _playerCards.toList()

    fun score(): Int {
        val score = cards.sumOf { it.score.point }
        return if (cards.hasAceCard()) {
            compareAbsoluteValue(score)
        } else score
    }

    private fun compareAbsoluteValue(score: Int): Int {
        val scoreContainBigValueOfAce = score - Card.CardDisplayValue.ACE.point + BIG_VALUE_OF_ACE
        return if (abs(BLACK_JACK - scoreContainBigValueOfAce) > abs(BLACK_JACK - score)) {
            score
        } else scoreContainBigValueOfAce
    }

    fun getCardDisplayName() = _playerCards.joinToString { "${it.score.displayName}${it.pattern.displayName}" }

    fun addCard() {
        _playerCards.add(cardDeck.draw())
    }

    private fun List<Card>.hasAceCard(): Boolean {
        return this.map { it.score }.contains(Card.CardDisplayValue.ACE)
    }

    companion object {
        fun of(playerName: String, cardDeck: Deck): Participant {
            return Participant(playerName, cardDeck = cardDeck)
        }

        const val BLACK_JACK = 21
        const val BIG_VALUE_OF_ACE = 11
    }
}
