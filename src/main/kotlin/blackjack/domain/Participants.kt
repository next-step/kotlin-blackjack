package blackjack.domain

import blackjack.domain.card.Card


data class Participant(
    val name: String,
    val playerCards: List<Card> = emptyList()
) {
    private val _playerCards = ArrayList<Card>()

    val cards: List<Card>
        get() = _playerCards.toList()

    fun score() = playerCards.sumOf { it.score.point }

    fun addCard(card: Card) {
        _playerCards.add(card)
    }

    companion object {
        fun of(playerName: String): Participant {
            return Participant(playerName)
        }
    }
}

