package blackjack.domain.result.match

import blackjack.domain.player.Dealer
import blackjack.domain.player.GamePlayer
import blackjack.domain.score.ScoreState

object BustMatchStrategy : MatchStrategy {
    override fun match(dealer: Dealer, player: GamePlayer): MatchState =
        if (player.scoreState != ScoreState.BUST) {
            MatchState.WIN
        } else {
            MatchState.LOSE
        }
}
