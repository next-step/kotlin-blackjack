package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Game
import blackjack.domain.GameMembers
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController() {
    fun start() {
        val game = createGame()
        OutputView.showGameStart(players = game.allPlayers())

        gameLoop(game)

        OutputView.showGameResult(players = game.allPlayers())
    }

    private fun gameLoop(game: Game) {
        game.allPlayers().allPlayers().forEach { player ->
            while (game.isPlayerStillPlaying(player)) {
                val hitCommand = InputView.askHitOrStay(player.name)
                game.processPlayerTurn(player, hitCommand)
                OutputView.printPlayerCards(player)
            }
        }
    }

    private fun createGame(): Game {
        val playerNames = InputView.getPlayerNames()
        val dealer = createDealer()
        val players = createPlayers(playerNames)
        val gameMembers = GameMembers(players, dealer)
        return Game(gameMembers)
    }

    private fun createDealer() = Dealer(Deck())

    private fun createPlayers(playerNames: List<String>) = Players(players = playerNames.map { Player(it) })
}
