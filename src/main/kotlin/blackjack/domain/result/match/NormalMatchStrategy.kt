package blackjack.domain.result.match

import blackjack.domain.player.Dealer
import blackjack.domain.player.GamePlayer
import blackjack.domain.score.ScoreState

object NormalMatchStrategy : MatchStrategy {
    override fun match(dealer: Dealer, player: GamePlayer): MatchState =
        if (player.scoreState == ScoreState.BUST || dealer.score > player.score) {
            MatchState.LOSE
        } else if (dealer.score == player.score) {
            MatchState.PUSH
        } else {
            MatchState.WIN
        }
}
