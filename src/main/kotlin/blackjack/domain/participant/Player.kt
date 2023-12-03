package blackjack.domain.participant

import blackjack.domain.GameResult
import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.state.Bust
import blackjack.domain.state.Started

class Player(
    name: ParticipantName,
    firstCard: Card,
    secondCard: Card,
) : Participant(
    name = name,
    state = Started.handCard(firstCard, secondCard)
) {

    fun isBust(): Boolean {
        return state is Bust
    }

    fun getResult(other: Score): GameResult.GameResultByPlayer {
        val name = name()
        val result = state.calculateResult(other)
        return GameResult.GameResultByPlayer(name, result)
    }
}
