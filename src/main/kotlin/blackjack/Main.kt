package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.CardDeck
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val input = InputView()
    val output = OutputView()
    val playerNames = input.initGamePlayer()
    val player1 = Player(name = playerNames.first())
    val player2 = Player(name = playerNames.last())
    val players = listOf(player1, player2)
    val cardDeck = CardDeck()

    val game = BlackjackGame(players, cardDeck)
    game.initPlayer()
    output.initGameSetting(players)

    players.forEach { player ->
        while (player.isGetCardPossible()) {
            val playerChoice = input.playerGetCard(player.name)
            if (playerChoice == "n") break
            game.giveCard(player)
            output.checkPlayerCard(listOf(player))
        }
    }

    output.showResult(players)
}