package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Game
import blackjack.domain.GameMembers
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {
    fun start() {
        val game = createGame()
        OutputView.showGameStart(players = game.allPlayers())

        gameLoop(game)
        dealerTurn(game)

        OutputView.showGameResult(players = game.allPlayers())

        OutputView.showWinnerPlayers(game.calculateDealerWinningScore(), game.calculateDealerLoseScore(), game.determineWinner())
    }

    private fun gameLoop(game: Game) {
        game.playersWithOutDealer().forEach { player ->
            while (game.isPlayerStillPlaying(player)) {
                val hitCommand = InputView.askHitOrStay(player.name)
                game.processPlayerTurn(player, hitCommand)
                OutputView.printPlayerCards(player)
            }
        }

        if (game.isDealerBust()) return
    }

    private fun dealerTurn(game: Game) {
        if (game.isDealerCardSumLessThan16()) {
            OutputView.dealerHitResult()
            game.dealerHit()
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
