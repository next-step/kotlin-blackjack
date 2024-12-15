package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController() {
    fun start() {
        val game = createGame()
        OutputView.showGameStart(players = game.players)

        gameLoop(game)

        OutputView.showGameResult(players = game.players)
    }

    private fun gameLoop(game: Game) {
        game.players.allPlayers().forEach { player ->
            while (game.isPlayerStillPlaying(player)) {
                val hitCommand = InputView.askHitOrStay(player.name)
                game.processPlayerTurn(player, hitCommand)
                OutputView.printPlayerCards(player)
            }
        }
    }

    private fun createGame(): Game {
        val playerNames = InputView.getPlayerNames()
        val gameMembers = createGameMembers(playerNames)
        return Game(gameMembers)
    }

    private fun createGameMembers(playerNames: List<String>): Players {
        val dealer = Dealer(Deck())
        return Players(listOf(dealer) + playerNames.map { Player(it) })
    }
}
