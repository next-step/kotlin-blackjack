package blackjack.domain.player

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards
import blackjack.domain.player.draw.DrawStrategyProvider
import blackjack.domain.score.ScoreState

class Player(val name: String, cards: Cards) {
    private var _cards = cards
    val cards
        get() = _cards

    val score
        get() = _cards.getScore()

    fun draw(deck: CardDeck, count: Int = 1) {
        val scoreState = _cards.getScoreState()
        val drawStrategy = DrawStrategyProvider[scoreState]
        _cards += drawStrategy.draw(deck, count)
    }

    fun isEligibleToHit(): Boolean {
        return score.getState() == ScoreState.NORMAL
    }
}
