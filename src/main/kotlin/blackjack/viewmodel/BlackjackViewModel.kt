package blackjack.viewmodel

import blackjack.domain.BlackjackGameTurn
import blackjack.domain.CardDeck
import blackjack.domain.HIT_CARD_COUNT
import blackjack.domain.Hands
import blackjack.domain.Observable
import blackjack.domain.Participant
import blackjack.domain.Participants
import blackjack.domain.PlayerName
import blackjack.domain.PlayingCard
import blackjack.domain.START_CARD_COUNT
import blackjack.domain.cardrule.DistinctRule
import blackjack.domain.cardrule.ShuffleRule

class BlackjackViewModel private constructor(
    val participants: Participants,
    private val cardDeck: CardDeck
) {
    val currentTurn: Observable<BlackjackGameTurn> = Observable(BlackjackGameTurn.from(participants))

    fun hit() {
        val currentPlayer = currentTurn.value.participant
        currentPlayer.receive(cardDeck.draw(HIT_CARD_COUNT))
    }

    fun stay() {
        val currentPlayer = currentTurn.value.participant as? Participant.Player // Todo : 수정해야 함
        currentPlayer?.stay()
    }

    fun nextTurn() {
        currentTurn.value = BlackjackGameTurn.from(participants)
    }

    companion object {
        fun from(dealerName: PlayerName, playerNames: List<PlayerName>): BlackjackViewModel {
            val cardDeck = CardDeck.of(
                PlayingCard.all(),
                DistinctRule, ShuffleRule
            )
            val participants = Participants(
                dealer = dealerName.toDealer(cardDeck),
                players = playerNames.toPlayers(cardDeck)
            )

            return BlackjackViewModel(
                participants,
                cardDeck
            )
        }

        fun of(dealerName: PlayerName, playerNames: List<PlayerName>, cardDeck: CardDeck): BlackjackViewModel {
            val participants = Participants(
                dealer = dealerName.toDealer(cardDeck),
                players = playerNames.toPlayers(cardDeck)
            )
            return BlackjackViewModel(participants, cardDeck)
        }

        private fun CardDeck.initialHands(): Hands {
            return Hands.from(draw(START_CARD_COUNT))
        }

        private fun PlayerName.toDealer(cardDeck: CardDeck): Participant.Dealer {
            return Participant.Dealer(this, cardDeck.initialHands())
        }

        private fun List<PlayerName>.toPlayers(cardDeck: CardDeck): List<Participant.Player> {
            return map { playerName ->
                Participant.Player(
                    name = playerName,
                    hands = cardDeck.initialHands()
                )
            }
        }
    }
}
