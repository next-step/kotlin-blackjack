package blackjack.service

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.PlayerState
import blackjack.domain.Players
import blackjack.domain.forEachPlayer
import blackjack.vo.BettingResultVO

class BettingService {
    private var dealerMoney = 0
    private var playersMoneys = mutableMapOf<Player, Int>()

    fun bettingResult(dealer: Dealer, players: Players): BettingResultVO {
        players.forEachPlayer {
            bettingResult(dealer, it)
        }

        return BettingResultVO(dealerMoney, playersMoneys)
    }

    private fun bettingResult(dealer: Dealer, player: Player) {
        when {
            player.state == PlayerState.BUST -> dealerGetMoney(player)
            dealer.state == PlayerState.BUST -> playerGetBettingMoney(player)
            player.state == PlayerState.STAND && player.sumOfMyCards() < dealer.sumOfMyCards() -> dealerGetMoney(player)
            player.state == PlayerState.BLACK_JACK && player.sizeOfMyCards() == 2 &&
                dealer.state == PlayerState.BLACK_JACK && dealer.sizeOfMyCards() == 2
            -> playerGetBettingMoney(player)
            player.state == PlayerState.BLACK_JACK && player.sizeOfMyCards() == 2 -> playerGetWinningMoney(player)
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
        dealerMoney -= (player.bettingMoney * 1.5).toInt()
        playersMoneys[player] = (player.bettingMoney * 1.5).toInt()
    }
}
