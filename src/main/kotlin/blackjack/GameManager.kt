package blackjack

import blackjack.card.CardDeck
import blackjack.ui.InputManager

class GameManager(
    private val inputManager: InputManager
) {
    private lateinit var players: List<Player>
    private val cardDeck: CardDeck = CardDeck()
    fun start() {
        joinPlayers()
    }

    private fun joinPlayers() {
        val playerNames: List<String> = inputManager.inputPlayerNames()
        this.players = playerNames.map(::Player)
    }
}

fun main() {
    GameManager(InputManager()).start()
}