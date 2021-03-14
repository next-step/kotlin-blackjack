package blackjack.domain

import blackjack.constant.BLACK_JACK_TWENTY_ONE
import blackjack.enums.CardType

class Player(
    private val name: String
) {
    private val cards = mutableSetOf<Card>()

    fun takeCard(card: Card): Boolean {
        return cards.add(card)
    }

    fun calculateCardSum(): Int {
        var cardPointSum = cards.sumBy { it.point }
        val aceCount = cards.count { it.type == CardType.Ace }

        repeat(aceCount) {
            cardPointSum = if (cardPointSum > BLACK_JACK_TWENTY_ONE) cardPointSum - CardType.DECREMENTABLE_POINT_OF_ACE else cardPointSum
        }
        return cardPointSum
    }
}