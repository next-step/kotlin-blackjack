package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.score.ScoreState

class GamePlayer(name: String, cards: Cards) : AbstractPlayer(name, cards) {
    override fun isEligibleToHit(): Boolean {
        return score.getState() == ScoreState.NORMAL
    }
}
