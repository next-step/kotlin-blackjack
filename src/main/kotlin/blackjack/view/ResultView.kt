package blackjack.view

import blackjack.model.player.Player
import blackjack.model.player.PlayerGameResults
import blackjack.model.player.PlayerName
import blackjack.model.player.Players

interface ResultView {
    fun printPlayersCardStatus(players: Players)

    fun printPlayerCardStatus(player: Player)

    fun printCardGameResult(results: PlayerGameResults)

    fun printDealerReceiveMoreCardMessage(dealerName: PlayerName)
}
