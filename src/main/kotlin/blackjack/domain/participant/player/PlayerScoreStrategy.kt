package blackjack.domain.participant.player

import blackjack.domain.participant.ScoreStrategy
import blackjack.domain.participant.dealer.Dealer
import blackjack.domain.participant.type.WinningScore
import blackjack.domain.participant.type.WinningScore.DRAW
import blackjack.domain.participant.type.WinningScore.LOSE
import blackjack.domain.participant.type.WinningScore.WIN
import blackjack.domain.participant.vo.WinningAmount

class PlayerScoreStrategy(val dealer: Dealer) : ScoreStrategy<Player> {
    override fun compare(player: Player) {
        player.winningAmount = when {
            player.participantInformation.isBust() -> LOSE
            dealer.participantInformation.isBust() -> WIN
            else -> WinningScore.valueOf(player.score.compareTo(dealer.score))
        }.let {
            when (it) {
                WIN -> WinningAmount(player.winBetAmount())
                DRAW -> if (player.isBlackJack) {
                    WinningAmount(player.betAmount.amount)
                } else {
                    WinningAmount(-player.betAmount.amount)
                }
                LOSE -> WinningAmount(-player.betAmount.amount)
            }
        }
    }
}
