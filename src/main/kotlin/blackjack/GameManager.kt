package blackjack

import blackjack.card.CardDeck
import blackjack.participant.Player
import blackjack.ui.InputManager
import blackjack.ui.OutputManager

class GameManager(
    private val inputManager: InputManager,
    private val outputManager: OutputManager
) {
    private val players: List<Player>

    init {
        players = joinPlayers()
    }

    fun start() {
        players.forEach { it.drawCard(CardDeck.draw(FIRST_DRAW)) }

        outputManager.printFirstTurn(players)
        outputManager.printPlayersCards(players)

        playBlackJack()

        players.forEach {
            outputManager.printPlayerResultGame(it)
        }
    }

    private fun playBlackJack() {
        players.forEach {
            playerDraw(it)
        }
    }

    private fun playerDraw(player: Player) {
        var drawAmount = -1

        while (player.shouldDraw() && drawAmount != 0) {
            drawAmount = inputManager.inputShouldDrawCard(player.name)
            if (playerChooseDraw(drawAmount)) {
                player.drawCard(CardDeck.draw(drawAmount))
            }
            outputManager.printPlayerCards(player)
        }
    }

    private fun playerChooseDraw(drawAmount: Int) = drawAmount > 0

    private fun joinPlayers(): List<Player> {
        val playerNames: List<String> = inputManager.inputPlayerNames()
        return playerNames.map { Player(it, ScoreCalculator()) }
    }

    companion object {
        private const val FIRST_DRAW: Int = 2
    }
}

fun main() {
    GameManager(InputManager(), OutputManager()).start()
}
