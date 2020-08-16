package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.Cards

abstract class Player(
    val name: String,
    protected val cards: Cards = Cards()
) {
    private var isFinish = false

    fun gameBatting(cards: Cards) {
        this.cards.addCards(cards)
    }

    abstract fun canMoreCard(): Boolean

    fun hit(card: Card) {
        cards.addCard(card)
    }

    fun done() {
        isFinish = true
    }

    fun getScore() = cards.getScore()

    override fun toString() = "${name}의 카드 : ${getCardsString()} ${getResult()}"

    private fun getResult() = if (isFinish) "- 결과 : ${getScore()}" else ""

    private fun getCardsString() = "$cards"
}
