package blackjack

import blackjack.card.CardDeck
import blackjack.ui.InputManager
import blackjack.ui.OutputManager

class GameManager(
    private val inputManager: InputManager,
    private val outputManager: OutputManager
) {
    private val players: List<Player>
    private val cardDeck: CardDeck = CardDeck()
    private val scoreCalculator: ScoreCalculator = ScoreCalculator()

    init {
        players = joinPlayers()
    }

    fun start() {
        players.forEach { it.drawCard(cardDeck.draw(FIRST_DRAW)) }

        outputManager.printFirstTurn(players)
        outputManager.printPlayersCards(players)

        playBlackJack()

        players.forEach {
            outputManager.printPlayerResultGame(it, scoreCalculator.calcScore(it.cards))
        }
    }

    private fun playBlackJack() {
        players.forEach {
            playerDraw(it)
        }
    }

    private fun playerDraw(player: Player) {
        var drawAmount = -1

        while (player.shouldDraw(scoreCalculator) && drawAmount != 0) {
            drawAmount = inputManager.inputShouldDrawCard(player.name)
            if (drawAmount > 0) {
                player.drawCard(cardDeck.draw(drawAmount))
            }
            outputManager.printPlayerCards(player)
        }
    }

    private fun joinPlayers(): List<Player> {
        val playerNames: List<String> = inputManager.inputPlayerNames()
        return playerNames.map(::Player)
    }

    companion object {
        private const val FIRST_DRAW: Int = 2
    }
}

fun main() {
    GameManager(InputManager(), OutputManager()).start()
}
