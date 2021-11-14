package blackjack.domain.player

import blackjack.domain.card.Card

data class Player(
    val name: String,
) {

    private var _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() {
            return _cards.toList()
        }

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun getCardSum(): Int {
        return _cards
            .sortedByDescending { card -> card.denomination.order }
            .fold(START_INDEX) { sum, card ->
                val denomination = card.denomination
                sum + denomination.getValue(sum)
            }
    }

    companion object {
        private const val START_INDEX = 0
    }
}
