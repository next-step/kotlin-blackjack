package blackjack.viewmodel

import blackjack.domain.CardDeck
import blackjack.domain.Observable
import blackjack.domain.Player
import blackjack.domain.PlayerName
import blackjack.domain.PlayingCard
import blackjack.domain.rule.DistinctRule
import blackjack.domain.rule.ShuffleRule

class BlackjackViewModel private constructor(
    val players: List<Player>,
    private val cardDeck: CardDeck
) {
    val currentTurn: Observable<Player?> = Observable(players.currentTurn())

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

        fun from(playerNames: List<PlayerName>): BlackjackViewModel {
            val cardDeck = CardDeck.of(
                PlayingCard.all(),
                DistinctRule, ShuffleRule
            )
            return BlackjackViewModel(playerNames.toPlayers(cardDeck), cardDeck)
        }

        private fun List<PlayerName>.toPlayers(cardDeck: CardDeck): List<Player> {
            return map { playerName ->
                Player(
                    name = playerName,
                    initialCards = cardDeck.draw(START_CARD_COUNT)
                )
            }
        }

        fun of(players: List<Player>, cardDeck: CardDeck): BlackjackViewModel {
            return BlackjackViewModel(players, cardDeck)
        }
    }
}
