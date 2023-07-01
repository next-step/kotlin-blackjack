package blackjack.service

import blackjack.domain.Dealer
import blackjack.domain.FightResult
import blackjack.domain.Player
import blackjack.domain.PlayerState
import blackjack.error.BlackjackErrorMessage.DEALER_STATUS_CAN_NOT_BE_STAND

class FightService {
    fun go(dealer: Dealer, player: Player): FightResult {
        return when (dealer.state) {
            PlayerState.BLACK_JACK -> dealerBlackjack(player)
            PlayerState.BUST -> dealerBust(dealer, player)
            PlayerState.HIT -> dealerHit(dealer, player)
            else -> throw IllegalStateException(DEALER_STATUS_CAN_NOT_BE_STAND)
        }
    }

    private fun dealerBlackjack(player: Player): FightResult {
        if (player.state == PlayerState.BLACK_JACK) {
            return FightResult.DRAW
        }

        return FightResult.LOSE
    }

    private fun dealerBust(dealer: Dealer, player: Player): FightResult {
        if (player.state == PlayerState.BUST) {
            return FightResult.LOSE
        }

        if (player.sumOfMyCards() == dealer.sumOfMyCards()) {
            return FightResult.DRAW
        }

        return FightResult.WIN
    }

    private fun dealerHit(dealer: Dealer, player: Player): FightResult {
        if (player.state == PlayerState.BUST) {
            return FightResult.LOSE
        }

        if (player.sumOfMyCards() == dealer.sumOfMyCards()) {
            return FightResult.DRAW
        }

        if (player.sumOfMyCards() > dealer.sumOfMyCards()) {
            return FightResult.WIN
        }

        return FightResult.LOSE
    }
}
