package service

import domain.participant.Dealer
import domain.participant.Player
import domain.participant.PlayerWinType

class BlackjackWinnerService {
    fun determineWinner(
        players: List<Player>,
        dealer: Dealer,
    ): Map<Player, PlayerWinType> =
        players.associateWith {
            when {
                it.isBust() -> PlayerWinType.LOSE
                dealer.isBust() -> PlayerWinType.WIN
                it.score() == dealer.score() -> PlayerWinType.DRAW
                it.score() > dealer.score() -> if (it.isBlackjack()) PlayerWinType.BLACKJACK_WIN else PlayerWinType.WIN
                else -> PlayerWinType.LOSE
            }
        }
}
