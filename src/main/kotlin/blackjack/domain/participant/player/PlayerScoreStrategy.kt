package blackjack.domain.participant.player

import blackjack.domain.participant.ScoreStrategy
import blackjack.domain.participant.dealer.Dealer
import blackjack.domain.participant.type.WinningScore
import blackjack.domain.participant.vo.WinningAmount

class PlayerScoreStrategy(val dealer: Dealer) : ScoreStrategy<Player> {
    override fun compare(player: Player) {
        player.winningAmount = when {
            player.participantInformation.isBust() -> WinningScore.LOSE
            dealer.participantInformation.isBust() -> WinningScore.WIN
            else -> WinningScore.valueOf(player.score.compareTo(dealer.score))
        }.let {
            when (it) {
                WinningScore.WIN -> WinningAmount(player.betAmount())
                WinningScore.DRAW -> if (player.isBlackJack) {
                    WinningAmount(player.betAmount())
                } else {
                    WinningAmount(-player.betAmount())
                }
                WinningScore.LOSE -> WinningAmount(-player.betAmount())
            }
        }
    }
}
