package blackjack.domains.participants

import blackjack.GameRule.Companion.BLACKJACK
import blackjack.GameScoreType
import blackjack.domains.deck.Cards
import blackjack.views.Input.answerDrawCard
import blackjack.views.Output.printHaveCards

class Player(
    override val name: String,
    override val cards: Cards = Cards()
) : User(name, cards) {
    lateinit var gameScore: GameScoreType
        private set

    override fun isDrawable(): Boolean {
        if (!cards.isDrawable(BLACKJACK)) return false
        if (!answerDrawCard(name)) return false
        return true
    }

    override fun printHasCards() {
        printHaveCards(name, cards)
    }

    override fun win() {
        gameScore = GameScoreType.WIN
    }

    override fun lose() {
        gameScore = GameScoreType.LOSE
    }

    override fun draw() {
        gameScore = GameScoreType.DRAW
    }
}
