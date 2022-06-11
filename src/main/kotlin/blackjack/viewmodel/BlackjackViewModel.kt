package blackjack.viewmodel

import blackjack.domain.CardDeck
import blackjack.domain.Observable
import blackjack.domain.Player
import blackjack.domain.PlayingCard
import blackjack.domain.rule.DistinctRule
import blackjack.domain.rule.ShuffleRule

class BlackjackViewModel private constructor(
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
        currentPlayer?.stay()
    }

    fun nextTurn() {
        currentTurn.value = players.currentTurn()
    }

    companion object {
        const val START_CARD_COUNT = 2
        private const val HIT_CARD_COUNT = 1

        fun from(players: List<Player>): BlackjackViewModel {
            val cardDeck = CardDeck.of(
                PlayingCard.all(),
                DistinctRule, ShuffleRule
            )
            return BlackjackViewModel(players, cardDeck)
        }

        fun of(players: List<Player>, cardDeck: CardDeck): BlackjackViewModel {
            return BlackjackViewModel(players, cardDeck)
        }
    }
}
