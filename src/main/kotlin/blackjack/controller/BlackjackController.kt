package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.Game
import blackjack.domain.GameMembers
import blackjack.domain.Participant.Dealer
import blackjack.domain.Participant.Player
import blackjack.domain.Participants
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {
    fun start() {
        val game = createGame()
        OutputView.showGameStart(participants = game.allPlayers())

        gameLoop(game)
        dealerTurn(game)

        OutputView.showGameResult(participants = game.allPlayers())

        OutputView.showWinnerPlayers(
            game.calculateDealerWinningScore(),
            game.calculateDealerLoseScore(),
            game.determineWinner(),
        )
    }

    private fun gameLoop(game: Game) {
        game.participants().forEach { player ->
            while (game.isPlayerStillPlaying(player)) {
                val hitCommand = InputView.askHitOrStay(player.name)
                game.processPlayerTurn(player, hitCommand)
                OutputView.printPlayerCards(player)
            }
        }

        if (game.isDealerBust()) return
    }

    private fun dealerTurn(game: Game) {
        if (game.isDealerDrawCard()) {
            OutputView.dealerHitResult()
            game.giveCardToDealer()
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

    private fun createPlayers(playerNames: List<String>) =
        Participants(
            players =
                playerNames.map {
                    Player(
                        it,
                    )
                },
        )
}
