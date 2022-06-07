package blackjack.viewmodel

import blackjack.domain.CardDeck
import blackjack.domain.Observable
import blackjack.domain.Player

class BlackjackViewModel(
    players: List<Player>,
    private val cardDeck: CardDeck
) {
    val players: List<Player> = players.withInitialHands()
    val currentTurn: Observable<Player?> = Observable(players.currentTurn())

    private fun List<Player>.withInitialHands(): List<Player> = onEach { player ->
        player.receive(cardDeck.draw(START_CARD_COUNT))
    }

    private fun List<Player>.currentTurn(): Player? = find { player ->
        player.isReceivable()
    }

    fun hit() {
        val currentPlayer = currentTurn.value
        currentPlayer?.receive(cardDeck.draw(HIT_CARD_COUNT))
    }

    fun stay() {
        val currentPlayer = currentTurn.value
        currentPlayer?.finish()
    }

    fun nextTurn() {
        currentTurn.value = players.currentTurn()
    }

    companion object {
        const val START_CARD_COUNT = 2
        private const val HIT_CARD_COUNT = 1
    }
}
