package blackjack.domain.player.draw

import blackjack.domain.score.ScoreState

object DrawStrategyProvider {
    operator fun get(scoreState: ScoreState): DrawStrategy = when (scoreState) {
        ScoreState.BUST -> BustDrawStrategy()
        ScoreState.BLACK_JACK -> BlackJackDrawStrategy()
        else -> NormalDrawStrategy()
    }
}
