package blackjack

import blackjack.domain.GamePlayers
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView.printDistributedCardsText
import blackjack.view.ResultView.printPlayersCards

fun main() {
    val blackJackApplication = BlackJackApplication()
    blackJackApplication.run()
}

class BlackJackApplication {
    fun run() {
        val playerNames = InputView.inputPlayerNames()
        val gamePlayers = GamePlayers.from(playerNames)
        val blackJackGame = BlackJackGame()

        printDistributedCardsText(playerNames)
        blackJackGame.distributeTwoCards(gamePlayers)
        printPlayersCards(gamePlayers)

        play(blackJackGame, gamePlayers)
        printPlayersCards(gamePlayers, isShownScore = true)
    }

    private fun play(
        blackJackGame: BlackJackGame,
        gamePlayers: GamePlayers,
    ) {
        gamePlayers.forEach { player ->
            hit(blackJackGame, player)
        }
    }

    private fun hit(
        blackJackGame: BlackJackGame,
        player: Player,
    ) {
        while (player.findEnabledMoreCard()) {
            if (!InputView.inputToProceed(player.name)) return
            blackJackGame.hit(player)
        }
    }
}
