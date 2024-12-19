package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.state.Hands
import blackjack.domain.state.Ready
import blackjack.domain.state.Running
import blackjack.domain.state.State

class BlackjackGame {
    private val deck = Deck()
    private val players = mutableMapOf<String, State>()

    fun initialize(names: List<String>) {
        names.forEach { name ->
            val initialState = Ready()
            players[name] = initialState.draw(deck.draw()).draw(deck.draw())
        }
    }

    fun getPlayersState(): Map<String, State> = players.toMap()

    fun getPlayerNames(): List<String> = players.keys.toList()

    fun getPlayerHands(name: String): Hands = players[name]?.hands ?: throw IllegalArgumentException("존재하지 않는 플레이어입니다.")

    fun isRunning(name: String): Boolean = players[name] is Running

    fun drawCard(name: String) {
        val currentState = players[name] ?: throw IllegalArgumentException("존재하지 않는 플레이어입니다.")
        players[name] = currentState.draw(deck.draw())
    }
}
