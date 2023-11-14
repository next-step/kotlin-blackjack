package view

import dto.GameResultDTO
import dto.GameStateDTO

class ConsoleOutputView : OutputView {
    override fun showGameState(gameState: GameStateDTO) {
        gameState.playerHands.forEach { (playerName, hand) ->
            println("${playerName}의 카드: ${hand.joinToString(", ") { it.toString() }}")
        }
    }

    override fun showGameResult(gameResult: GameResultDTO) {
        println("게임 결과:")
        gameResult.finalScores.forEach { (playerName, scoreAndHand) ->
            val (hand, score) = scoreAndHand
            println("$playerName: ${hand.joinToString(", ")} - 결과: $score")
        }
    }

    override fun showInitialCards(gameState: GameStateDTO) {
        val playerNames = gameState.playerHands.keys.joinToString(", ")
        println("$playerNames 에게 2장의 카드를 나누었습니다.")
        gameState.playerHands.forEach { (playerName, hand) ->
            println("${playerName}의 카드: ${hand.joinToString(", ") { it.toString() }}")
        }
    }
}
