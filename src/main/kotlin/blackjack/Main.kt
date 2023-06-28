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
    val players = playerNames.map { Player(name = it) }
    val cardDeck = CardDeck()

    val game = BlackjackGame(players, cardDeck)
    game.initPlayer()
    output.initGameSetting(players)

    players.forEach { player ->
        game.choiceTurn(player, input, output)
    }
    output.showResult(players)
}