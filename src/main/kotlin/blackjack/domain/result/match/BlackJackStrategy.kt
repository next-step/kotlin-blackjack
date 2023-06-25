package blackjack.domain.result.match

import blackjack.domain.player.Dealer
import blackjack.domain.player.GamePlayer
import blackjack.domain.score.ScoreState

object BlackJackStrategy : MatchStrategy {
    override fun match(dealer: Dealer, player: GamePlayer): MatchState =
        if (player.scoreState == ScoreState.BLACK_JACK) {
            MatchState.PUSH
        } else {
            MatchState.LOSE
        }
}
