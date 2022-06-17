package blackjack.view

import blackjack.model.player.Player
import blackjack.model.player.Players

interface ResultView {
    fun printPlayersCardStatus(players: Players)

    fun printPlayerCardStatus(player: Player)

    fun printCardGameResult(players: Players)
}
