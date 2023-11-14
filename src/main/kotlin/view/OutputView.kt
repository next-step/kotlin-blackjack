package view

import dto.GameResultDTO
import dto.GameStateDTO

interface OutputView {
    fun showGameState(gameState: GameStateDTO)
    fun showGameResult(gameResult: GameResultDTO)
    fun showInitialCards(gameState: GameStateDTO)
}
