package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.EarningCalculator
import blackjack.domain.EarningMoneyResult
import blackjack.domain.Game
import blackjack.domain.GameMembers
import blackjack.domain.Participant.Dealer
import blackjack.domain.Participant.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {
    fun start() {
        val game = createGame()
        OutputView.showGameStart(participants = game.allPlayers())

        gameLoop(game)
        dealerTurn(game)

        OutputView.showGameResult(participants = game.allPlayers())

        OutputView.showDealerWinningCount(game.determineDealerWinningOutcome())

        OutputView.showWinnerPlayers(
            game.determineWinner(),
        )

        OutputView.showEarningMoneyResult(toEarningMoneyResults(game))
    }

    private fun toEarningMoneyResults(game: Game): List<EarningMoneyResult> {
        val earningCalculator = EarningCalculator(game.dealer())
        val dealerEarningMoneyResults = EarningMoneyResult(game.dealer(), earningCalculator.dealerMoney(game.players()))
        val playerEarningMoneyResults =
            game.players().map {
                val money = earningCalculator.calculatePlayerEarnings(it)
                EarningMoneyResult(it, money)
            }
        return listOf(dealerEarningMoneyResults) + playerEarningMoneyResults
    }

    private fun gameLoop(game: Game) {
        game.participants().members.forEach { participant ->
            while (game.isPlayerStillPlaying(participant)) {
                val hitCommand = InputView.askHitOrStay(participant)
                game.processPlayerTurn(participant, hitCommand)
                OutputView.printPlayerCards(participant)
            }
        }
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

    private fun createPlayers(playerNames: List<String>): List<Player> {
        return playerNames.map { name ->
            val bettingAmount = InputView.askBettingAmount(name)
            Player(name = name, bettingAmount = bettingAmount)
        }
    }
}
