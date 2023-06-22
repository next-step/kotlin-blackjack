package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.card.CardDeck
import blackjack.domain.player.Player
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

        val playerList = playerNameList.map { Player(it) }
        val deck = CardDeck()
        val blackjackGame = BlackjackGame(playerList, deck)
        blackjackGame.initPlayers()
        GameSharedCardOutputView(playerList)
        playerList.forEach { player -> PlayerOutputView(player) }
        NewLineOutputView()

        playerList.forEach { player -> dealCards(player, blackjackGame) }
        playerList.forEach { player -> PlayerResultOutputView(player) }
    }

    fun dealCards(player: Player, blackjackGame: BlackjackGame) {
        while (player.isReceivable()) {
            val response = GamePlayerReceiveInputView(player.name).value
            if (!response.value) return
            blackjackGame.dealCards(player)
            PlayerOutputView(player)
        }
    }
}
