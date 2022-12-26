package blackjack.domain.player

import blackjack.domain.GameOverPlayerException
import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Player(
    private val name: PlayerName,
    private val cards: Cards = Cards()
) {
    private var finished: Boolean = false

    constructor(inputName: String) : this(name = PlayerName(inputName))
    constructor(inputName: String, cards: Cards) : this(name = PlayerName(inputName), cards = cards)

    fun getName() = name.getValue()
    fun getCards() = cards.getCards()
    fun isFinished() = finished
    fun getScore() = cards.getScore()

    fun hit(newCard: Card) {
        if (finished) throw GameOverPlayerException()

        cards.add(newCard)
        checkCardIsFull()
    }

    private fun checkCardIsFull() {
        if (cards.isFull()) this.finished = true
    }

    fun stay() {
        this.finished = true
    }

    fun isBlackJack() = cards.isBlackJack()
}
