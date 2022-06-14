package blackjack.domain.participant.dealer

import blackjack.domain.participant.ScoreStrategy
import blackjack.domain.participant.player.Players
import blackjack.domain.participant.type.WinningScore
import blackjack.domain.participant.type.WinningScore.DRAW
import blackjack.domain.participant.type.WinningScore.LOSE
import blackjack.domain.participant.type.WinningScore.WIN
import blackjack.domain.participant.vo.WinningAmount

class DealerScoreStrategy(val players: Players) : ScoreStrategy<Dealer> {
    override fun compare(dealer: Dealer) {
        players.players.forEach { player ->
            dealer.winningAmount = when {
                player.participantInformation.isBust() -> WIN
                dealer.participantInformation.isBust() -> LOSE
                else -> WinningScore.valueOf(dealer.score.compareTo(player.score))
            }.let {
                when (it) {
                    WIN -> WinningAmount(dealer.winningAmount.amount + player.betAmount.amount)
                    DRAW -> if (player.isBlackJack) {
                        WinningAmount(dealer.winningAmount.amount - player.betAmount.amount)
                    } else {
                        WinningAmount(dealer.winningAmount.amount + player.betAmount.amount)
                    }
                    LOSE -> WinningAmount(dealer.winningAmount.amount - player.winBetAmount())
                }
            }
        }
    }
}
