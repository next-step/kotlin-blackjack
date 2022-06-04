package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Deck


data class Participant(
    val name: String,
    private val playerCards: List<Card> = emptyList(),
    private val deck: Deck
) {
    private val _playerCards = ArrayList<Card>()

    val cards: List<Card>
        get() = _playerCards.toList()

    fun score() = playerCards.sumOf { it.score.point }

    fun getCardDisplayName() = _playerCards.joinToString { "${it.score.point}${it.pattern.displayName}" }

    fun addCard() {
        _playerCards.add(deck.draw())
    }

    companion object {
        fun of(playerName: String, deck: Deck): Participant {
            return Participant(playerName, deck = deck)
        }
    }
}

