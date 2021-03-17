package blackjack.domain.player

import blackjack.domain.card.Card

class Player(val playerName: PlayerName) {
    private val _hands = mutableListOf<Card>()
    val hands
        get() = _hands.toList()

    fun draw(card: Card) = _hands.add(card)
}
