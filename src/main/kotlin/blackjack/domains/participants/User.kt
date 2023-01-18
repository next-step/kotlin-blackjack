package blackjack.domains.participants

import blackjack.domains.deck.Card
import blackjack.domains.deck.Cards

abstract class User(
    open val name: String, open val cards: Cards = Cards()
) {

    var summedCardNumbers = 0
        private set

    fun startGame(cards: Cards) {
        cards.values.forEach { drawCard(it) }
    }

    fun drawCard(card: Card) {
        cards.addCard(card)
    }

    fun setSummedCardNumbers(summed: Int) {
        summedCardNumbers = summed
    }

    abstract fun isDrawable(): Boolean

    abstract fun printHasCards()

    abstract fun win()
    abstract fun lose()
    abstract fun draw()
}
