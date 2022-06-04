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
        if (cards.hasAceCard()) {
            val containAcePoint = score - Card.CardDisplayValue.ACE.point + CUSTOM_ACE_POINT
            return if (abs(BLACK_JACK - containAcePoint) > abs(BLACK_JACK - score)) {
                score
            } else containAcePoint
        }
        return score
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
        const val CUSTOM_ACE_POINT = 11
    }
}
