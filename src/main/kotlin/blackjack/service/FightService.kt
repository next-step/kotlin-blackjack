package blackjack.service

import blackjack.domain.Dealer
import blackjack.domain.FightResult
import blackjack.domain.Player
import blackjack.domain.PlayerState

class FightService {
    fun go(dealer: Dealer, player: Player): FightResult {
        return when (dealer.findStateBySum()) {
            PlayerState.BLACK_JACK -> dealerBlackjack(player)
            PlayerState.BUST -> dealerBust(dealer, player)
            PlayerState.STAND -> dealerStand(dealer, player)
            else -> throw IllegalStateException()
        }
    }

    private fun dealerBlackjack(player: Player): FightResult {
        if (player.findStateBySum() == PlayerState.BLACK_JACK) {
            return FightResult.DRAW
        }

        return FightResult.LOSE
    }

    private fun dealerBust(dealer: Dealer, player: Player): FightResult {
        if (player.findStateBySum() == PlayerState.BUST) {
            return FightResult.LOSE
        }

        if (player.sumOfMyCards() == dealer.sumOfMyCards()) {
            return FightResult.DRAW
        }

        return FightResult.WIN
    }

    private fun dealerStand(dealer: Dealer, player: Player): FightResult {
        if (player.findStateBySum() == PlayerState.BUST) {
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
