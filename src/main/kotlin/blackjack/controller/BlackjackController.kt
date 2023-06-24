package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.view.input.GamePlayerNameInputView
import blackjack.view.input.GamePlayerReceiveInputView
import blackjack.view.output.GameSharedCardOutputView
import blackjack.view.output.NewLineOutputView
import blackjack.view.output.PlayerOutputView
import blackjack.view.output.PlayerResultOutputView

class BlackjackController {
    fun start() {
        val playerNameList = GamePlayerNameInputView().value
        NewLineOutputView()

        val playerList = Players(playerNameList.map { Player(it) })
        val blackjackGame = BlackjackGame(playerList)
        blackjackGame.initPlayers()
        GameSharedCardOutputView(playerList)
        playerList.value.forEach { player -> PlayerOutputView(player) }
        NewLineOutputView()

        playerList.value.forEach { player -> dealCards(player, blackjackGame) }
        NewLineOutputView()
        playerList.value.forEach { player -> PlayerResultOutputView(player) }
    }

    fun dealCards(player: Player, blackjackGame: BlackjackGame) {
        while (player.isReceivable()) {
            val response = GamePlayerReceiveInputView(player.name).value
            if (!response.value) {
                player.getStayStatus()
                return
            }
            blackjackGame.dealCard(player)
            PlayerOutputView(player)
        }
    }
}
