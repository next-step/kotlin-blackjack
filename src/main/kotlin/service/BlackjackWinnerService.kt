package service

import domain.participant.Dealer
import domain.participant.ParticipantHand
import domain.participant.Player
import domain.participant.WinType

class BlackjackWinnerService {
    fun determineWinner(
        players: List<Player>,
        dealer: Dealer,
    ): Map<Player, WinType> =
        players.associateWith {
            when {
                dealer.score() > ParticipantHand.BLACKJACK_MAX_SCORE -> WinType.WIN
                it.score() > ParticipantHand.BLACKJACK_MAX_SCORE -> WinType.LOSE
                it.score() == dealer.score() -> WinType.DRAW
                it.score() > dealer.score() -> WinType.WIN
                else -> WinType.LOSE
            }
        }
}
