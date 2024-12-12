package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Game
import blackjack.domain.HitCommand
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(private val inputView: InputView, private val outputView: OutputView) {
    fun start() {
        val game = createGame()
        outputView.showGameStart(players = game.players)

        gameLoop(game)
    }

    private fun gameLoop(game: Game) {
        game.players.allPlayers().forEach { player ->
            while (!game.isPlayerDone(player)) {
                val hitCommand = inputView.askHitOrStay(player.name)
                game.processPlayerTurn(player, HitCommand.from(hitCommand))
                outputView.printPlayerCards(player)
            }
        }
    }

    private fun createGame(): Game {
        val playerNames = inputView.getPlayerNames()
        val dealer = createDealer()
        val players = createPlayers(playerNames)
        return Game(dealer = dealer, players = players)
    }

    private fun createDealer() = Dealer(Deck())

    private fun createPlayers(playerNames: List<String>) = Players(playerNames.map { Player(it) })
}
