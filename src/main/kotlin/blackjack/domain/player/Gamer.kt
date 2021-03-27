package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Cards

interface Gamer {

    val name: String
    val cards: Cards

    fun isTakeable(): Boolean
    fun takeCard(card: Card) = cards.add(card)
}
