package blackjack.viewmodel

import blackjack.domain.CardDeck
import blackjack.domain.Hands
import blackjack.domain.Observable
import blackjack.domain.Participant
import blackjack.domain.PlayerName
import blackjack.domain.PlayingCard
import blackjack.domain.rule.DistinctRule
import blackjack.domain.rule.ShuffleRule

class BlackjackViewModel private constructor(
    val players: List<Participant>,
    private val cardDeck: CardDeck
) {
    val currentTurn: Observable<Participant?> = Observable(players.currentTurn())

    private fun List<Participant>.currentTurn(): Participant? = find { player ->
        player.isReceivable()
    }

    fun hit() {
        val currentPlayer = currentTurn.value
        currentPlayer?.receive(cardDeck.draw(HIT_CARD_COUNT))
    }

    fun stay() {
        val currentPlayer = currentTurn.value as? Participant.Player // Todo : 수정해야 함
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

        private fun List<PlayerName>.toPlayers(cardDeck: CardDeck): List<Participant> {
            return map { playerName ->
                Participant.Player(
                    name = playerName,
                    hands = Hands.from(cardDeck.draw(START_CARD_COUNT))
                )
            }
        }

        fun of(playerNames: List<PlayerName>, cardDeck: CardDeck): BlackjackViewModel {
            return BlackjackViewModel(playerNames.toPlayers(cardDeck), cardDeck)
        }
    }
}
