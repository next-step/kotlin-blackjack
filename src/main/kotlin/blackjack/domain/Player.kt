package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardRank

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
    fun sum(): Int {
        val hasAce = cards.any { it.rank == CardRank.ACE }
        val sum = cards.sumOf { it.rank.value }

        return if (hasAce && sum + 10 <= 21) sum + 10 else sum
    }

    fun stopDraw() { state = PlayerState.STOP }
}
