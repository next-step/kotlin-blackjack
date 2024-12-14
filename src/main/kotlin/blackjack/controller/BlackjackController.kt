package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Game
import blackjack.domain.HitCommand
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
                game.processPlayerTurn(player, HitCommand.from(hitCommand))
                OutputView.printPlayerCards(player)
            }
        }
    }

    private fun createGame(): Game {
        val playerNames = InputView.getPlayerNames()
        val dealer = createDealer()
        val players = createPlayers(playerNames)
        return Game(dealer = dealer, players = players)
    }

    private fun createDealer() = Dealer(Deck())

    private fun createPlayers(playerNames: List<String>) = Players(playerNames.map { Player(it) })
}
