package blackjack.service

import blackjack.domain.Dealer
import blackjack.domain.FightResult
import blackjack.domain.ParticipantState
import blackjack.domain.Player
import blackjack.error.BlackjackErrorMessage.DEALER_STATUS_CAN_NOT_BE_STAND

class FightService {
    fun go(dealer: Dealer, player: Player): FightResult {
        return when (dealer.state) {
            ParticipantState.BLACK_JACK -> dealerBlackjack(player)
            ParticipantState.BUST -> dealerBust(dealer, player)
            ParticipantState.HIT -> dealerHit(dealer, player)
            else -> throw IllegalStateException(DEALER_STATUS_CAN_NOT_BE_STAND)
        }
    }

    private fun dealerBlackjack(player: Player): FightResult {
        if (player.state == ParticipantState.BLACK_JACK) {
            return FightResult.DRAW
        }

        return FightResult.LOSE
    }

    private fun dealerBust(dealer: Dealer, player: Player): FightResult {
        if (player.state == ParticipantState.BUST) {
            return FightResult.LOSE
        }

        if (player.sumOfCards() == dealer.sumOfCards()) {
            return FightResult.DRAW
        }

        return FightResult.WIN
    }

    private fun dealerHit(dealer: Dealer, player: Player): FightResult {
        if (player.state == ParticipantState.BUST) {
            return FightResult.LOSE
        }

        if (player.sumOfCards() == dealer.sumOfCards()) {
            return FightResult.DRAW
        }

        if (player.sumOfCards() > dealer.sumOfCards()) {
            return FightResult.WIN
        }

        return FightResult.LOSE
    }
}
