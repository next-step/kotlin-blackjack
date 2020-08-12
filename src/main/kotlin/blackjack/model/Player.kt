package blackjack.model

import blackjack.model.card.Card
import blackjack.model.card.Cards

class Player(
    val name: String
) {
    private val cards = Cards()

    fun gameBatting(cardsDummy: Cards) {
        cards.addCards(cardsDummy)
    }

    fun hit(card: Card) {
        cards.addCard(card)
    }

    fun getScore() = cards.getScore()

    override fun toString() = "${name}의 카드 : ${getCardsString()}"

    private fun getCardsString() = "$cards"
}
