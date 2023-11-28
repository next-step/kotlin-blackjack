package ui

import blackjack.game.BlackjackGame
import blackjack.game.DealerStrategyType
import blackjack.game.GameState
import blackjack.game.blackjackOpen
import ui.input.InputView
import ui.result.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()
    val playerNames = inputView.inputPlayerNames()

    val blackjackGame = blackjackOpen {
        join(playerNames)
        dealerStrategy(DealerStrategyType.DEFAULT_DEALER_STRATEGY)
    }

    while (blackjackGame.state !is GameState.End) {
        processGameState(blackjackGame, inputView, resultView)
    }
    processGameState(blackjackGame, inputView, resultView)
}

private fun processGameState(blackjackGame: BlackjackGame, inputView: InputView, resultView: ResultView) {
    when (val gameState = blackjackGame.state) {
        is GameState.PlayerTurn -> processPlayerTurn(gameState, blackjackGame, inputView, resultView)
        is GameState.DealerTurn -> processDealerTurn(blackjackGame, resultView)
        is GameState.InitialDeal -> processInitialDeal(blackjackGame, resultView)
        is GameState.End -> processGameEnd(blackjackGame, resultView)
    }
}

private fun processPlayerTurn(
    gameState: GameState.PlayerTurn,
    blackjackGame: BlackjackGame,
    inputView: InputView,
    resultView: ResultView
) {
    val currentPlayer = gameState.currentPlayer
    val isDeal = inputView.askForAdditionalCard(currentPlayer.name)
    blackjackGame.dealPlayerTurn(currentPlayer, isDeal)
    resultView.showCards(currentPlayer.name, blackjackGame.showPlayerCards(currentPlayer.name))
}

private fun processDealerTurn(blackjackGame: BlackjackGame, resultView: ResultView) {
    val action = blackjackGame.dealDealerTurn()
    resultView.showDealerTurn(action)
}

private fun processInitialDeal(blackjackGame: BlackjackGame, resultView: ResultView) {
    blackjackGame.dealInitialCards()
    resultView.showInitialDealMessage(blackjackGame.players)
    resultView.showCards(blackjackGame.dealer)
    resultView.showCards(blackjackGame.players)
}

private fun processGameEnd(blackjackGame: BlackjackGame, resultView: ResultView) {
    println()
    val result = blackjackGame.calculateResult()

    resultView.showCards(blackjackGame.dealer)
    blackjackGame.players.forEach { resultView.showCards(it) }

    resultView.showParticipantsRecord(blackjackGame, result)
}
