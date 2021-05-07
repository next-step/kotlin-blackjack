package blackjack.model.gamer

import blackjack.model.BetMoney
import blackjack.model.score.Score
import blackjack.model.state.State
import blackjack.model.trump.Cards
import blackjack.model.trump.Deck

class Dealer(name: String = "딜러") : Gamer {
    private var gamer: Player = Player(name, BetMoney.ZERO)

    override val isBlackJack: Boolean
        get() = gamer.isBlackJack

    override val isBust: Boolean
        get() = gamer.isBust

    override val cards: Cards
        get() = gamer.cards

    override var state: State
        get() = gamer.state
        set(value) { gamer.state = value }

    override val name: String
        get() = gamer.name

    override val betMoney: BetMoney
        get() = gamer.betMoney

    fun drawIfNeeded(deck: Deck): Boolean {
        if (getScore() < MINIMUM_SCORE) {
            draw(deck)

            return true
        }
        return false
    }

    fun copy(betMoney: BetMoney): Dealer {
        return Dealer().apply { gamer = this@Dealer.gamer.copy(betMoney = betMoney) }
    }

    override fun keepDrawing(userResponse: Boolean, deck: Deck): Boolean = gamer.keepDrawing(userResponse, deck)

    override fun getScore(): Score = gamer.getScore()

    override fun calculateRevenue(): BetMoney = gamer.calculateRevenue()

    override fun draw(deck: Deck) = gamer.draw(deck)

    companion object {
        val MINIMUM_SCORE = Score(16)
    }
}
