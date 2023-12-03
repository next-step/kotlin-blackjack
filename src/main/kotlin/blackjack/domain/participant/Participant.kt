package blackjack.domain.participant

import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.state.State

sealed class Participant(
    private val name: ParticipantName,
    protected var state: State
) {
    fun name(): String {
        return name.value
    }

    fun cards(): Cards {
        return state.cards()
    }

    fun calculateScore(): Score {
        return cards().calculateScore()
    }

    fun receiveCard(card: Card) {
        state = state.receiveCard(card)
    }

    fun stay() {
        state = state.stay()
    }
}
