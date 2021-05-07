package blackjack.model.gamer

import blackjack.model.BetMoney
import blackjack.model.score.Score
import blackjack.model.state.BlackJack
import blackjack.model.state.Bust
import blackjack.model.state.Initial
import blackjack.model.state.State
import blackjack.model.trump.Cards
import blackjack.model.trump.Deck

data class Player(override val name: String, override val betMoney: BetMoney, override var state: State = Initial()) : Gamer {
    override val isBlackJack: Boolean
        get() = state is BlackJack

    override val isBust: Boolean
        get() = state is Bust

    override val cards: Cards
        get() = state.cards

    constructor(source: PlayerBuildSource) : this(source.name, source.betMoney)

    override fun getScore(): Score = state.score

    override fun calculateRevenue(): BetMoney = state.calculateRevenue(betMoney)

    override fun keepDrawing(userResponse: Boolean, deck: Deck): Boolean {
        if (userResponse) {
            draw(deck)
        }
        return userResponse
    }

    override fun draw(deck: Deck) {
        deck.draw()?.let {
            state = state.add(it)
        }
    }
}
