package blackjack.domain.player

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards
import blackjack.domain.player.draw.DrawStrategyProvider

abstract class AbstractPlayer(val name: String, cards: Cards) {
    protected var _cards = cards
    val cards
        get() = _cards

    val score
        get() = _cards.getScore()

    val scoreState
        get() = score.getState()

    fun draw(deck: CardDeck, count: Int = 1) {
        val scoreState = _cards.getScoreState()
        val drawStrategy = DrawStrategyProvider[scoreState]
        _cards += drawStrategy.draw(deck, count)
    }

    abstract fun isEligibleToHit(): Boolean
}
