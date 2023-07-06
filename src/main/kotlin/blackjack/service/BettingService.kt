package blackjack.service

import blackjack.domain.Dealer
import blackjack.domain.ParticipantState
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.vo.GameProfitResult

class BettingService {
    private var dealerMoney = 0
    private var playersMoneys = mutableMapOf<Player, Int>()

    fun bettingResult(dealer: Dealer, players: Players): GameProfitResult {
        players.forEach {
            bettingResult(dealer, it)
        }

        return GameProfitResult(dealerMoney, playersMoneys)
    }

    private fun bettingResult(dealer: Dealer, player: Player) {
        when {
            player.state == ParticipantState.BUST -> dealerGetMoney(player)
            dealer.state == ParticipantState.BUST -> playerGetBettingMoney(player)
            player.state == ParticipantState.STAND && player.sumOfCards() < dealer.sumOfCards() -> dealerGetMoney(player)
            player.state == ParticipantState.BLACK_JACK && player.sizeOfCards() == 2 &&
                dealer.state == ParticipantState.BLACK_JACK && dealer.sizeOfCards() == 2
            -> playerGetBettingMoney(player)
            player.state == ParticipantState.BLACK_JACK && player.sizeOfCards() == 2 -> playerGetWinningMoney(player)
            else -> playerGetBettingMoney(player)
        }
    }

    private fun playerGetBettingMoney(player: Player) {
        dealerMoney -= player.bettingMoney
        playersMoneys[player] = player.bettingMoney
    }

    private fun dealerGetMoney(player: Player) {
        dealerMoney += player.bettingMoney
        playersMoneys[player] = player.bettingMoney * (-1)
    }

    private fun playerGetWinningMoney(player: Player) {
        dealerMoney -= (player.bettingMoney * WINNING_PRIZE_MULTIPLIER).toInt()
        playersMoneys[player] = (player.bettingMoney * WINNING_PRIZE_MULTIPLIER).toInt()
    }

    companion object {
        private const val WINNING_PRIZE_MULTIPLIER: Double = 1.5
    }
}
