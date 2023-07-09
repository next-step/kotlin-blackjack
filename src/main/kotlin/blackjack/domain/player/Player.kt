package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Deck

class Player(
    val name: String,
    private var hasAce: Boolean = false,
    val cards: MutableList<Card> = mutableListOf()
) {

    var isShow: Boolean = false
    fun drawCard(deck: Deck) {
        cards.add(deck.getOneCard())
    }

    fun getCardsValue(): Int {
        val total = cards.sumOf { it.getValue() }
        if (hasAce && total + 10 <= 21) {
            return total + 10
        }
        return total
    }

    companion object {
        fun of(name: String, deck: Deck): Player {
            return Player(name, cards = mutableListOf(deck.getOneCard(), deck.getOneCard()))
        }
    }
}
