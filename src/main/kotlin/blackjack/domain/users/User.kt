package blackjack.domain.users

import blackjack.domain.Card
import blackjack.domain.Cards

abstract class User(
    val name: String,
    var cards: Cards
) {

    fun cardList(): List<Card> {
        return cards.cards
    }

    fun cardSize(): Int {
        return cards.size
    }

    fun plusCard(card: Card) {
        cards = cards.plusCard(card)
    }

    fun cardValues(): Int {
        return cards.value()
    }
}
