package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardDenomination

data class Player(
    val name: String,
    val cards: List<Card> = emptyList()
) {
    fun getCardSum(): Int {
        var sum = 0
        val sortedCards = cards
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
