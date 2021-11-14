package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardDenomination

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
        var sum = 0
        val sortedCards = _cards
            .sortedByDescending { card -> card.denomination.order }

        for (card in sortedCards) {
            val denomination = card.denomination

            sum += if (denomination == CardDenomination.ACE) {
                if (sum + denomination.value[1] > 21) {
                    denomination.value[0]
                } else {
                    denomination.value[1]
                }
            } else {
                denomination.value[0]
            }
        }

        return sum
    }
}
