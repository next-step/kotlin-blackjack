package blackjack.domain.result.match

import blackjack.domain.player.Dealer
import blackjack.domain.score.ScoreState

object MatchStrategyProvider {
    operator fun get(dealer: Dealer) = when (dealer.scoreState) {
        ScoreState.BUST -> BustMatchStrategy
        ScoreState.NORMAL -> NormalMatchStrategy
        ScoreState.BLACK_JACK -> BlackJackStrategy
    }
}
