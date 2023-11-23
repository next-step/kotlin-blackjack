package blackjack

import blackjack.card.CardDeck
import blackjack.ui.InputManager
import blackjack.ui.OutputManager
import java.util.PrimitiveIterator

class GameManager(
    private val inputManager: InputManager,
    private val outputManager: OutputManager
) {
    private lateinit var players: List<Player>
    private val cardDeck: CardDeck = CardDeck()
    fun start() {
        joinPlayers()
        players.forEach { it.drawCard(cardDeck.draw(FIRST_DRAW)) }

        outputManager.printFirstTurn(players)
        outputManager.printPlayersCards(players)
    }

    private fun joinPlayers() {
        val playerNames: List<String> = inputManager.inputPlayerNames()
        this.players = playerNames.map(::Player)
    }

    companion object {
        private const val FIRST_DRAW: Int = 2
    }
}

fun main() {
    GameManager(InputManager(), OutputManager()).start()
}
