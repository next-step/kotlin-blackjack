package blackjack.domain

import blackjack.domain.card.Card

class Player(
    val name: String = "player"
) {
    var state: PlayerState = PlayerState.PLAYING
        get() {
            if (field == PlayerState.PLAYING && sum() > 21) field = PlayerState.BURST
            return field
        }
        private set

    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards.toList()

    fun addCards(cards: List<Card>): Boolean = _cards.addAll(cards)
    fun addCard(card: Card): Boolean = _cards.add(card)
    fun sum(): Int = cards.sumOf { it.rank.value }

    fun stopDraw() { state = PlayerState.STOP }
}
