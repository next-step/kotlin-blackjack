package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.Cards

abstract class Player(
    val name: String,
    protected val cards: Cards = Cards()
) {
    abstract fun gameBatting(cards: Cards)

    fun canMoreCard() = cards.isBurst().not()

    fun hit(card: Card) {
        cards.addCard(card)
    }

    fun getScore() = cards.getScore()

    override fun toString() = "${name}의 카드 : ${getCardsString()}"

    private fun getCardsString() = "$cards"
}
