package blackjack.domain.participant.dealer

import blackjack.domain.participant.ScoreStrategy
import blackjack.domain.participant.player.Players
import blackjack.domain.participant.type.WinningScore
import blackjack.domain.participant.vo.WinningAmount

class DealerScoreStrategy(val players: Players) : ScoreStrategy<Dealer> {
    override fun compare(dealer: Dealer) {
        players.players.forEach { player ->
            dealer.winningAmount = when {
                player.participantInformation.isBust() -> WinningScore.WIN
                dealer.participantInformation.isBust() -> WinningScore.LOSE
                else -> WinningScore.valueOf(dealer.score.compareTo(player.score))
            }.let {
                when (it) {
                    WinningScore.WIN -> WinningAmount(dealer.winningAmount.amount + player.betAmount())
                    WinningScore.DRAW -> if (player.isBlackJack) {
                        WinningAmount(dealer.winningAmount.amount - player.betAmount())
                    } else {
                        WinningAmount(dealer.winningAmount.amount + player.betAmount())
                    }
                    WinningScore.LOSE -> WinningAmount(dealer.winningAmount.amount - player.betAmount())
                }
            }
        }
    }
}
