package blackjack.domain.participant

import blackjack.domain.GameResult
import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.state.Started

class Player(
    name: ParticipantName,
    firstCard: Card,
    secondCard: Card,
): Participant(
    name = name,
    state = Started.handCard(firstCard, secondCard)
) {

    fun isBust(): Boolean {
        return cards().isBust()
    }

    fun compareScore(other: Score): GameResult.GameResultByPlayer {
        val score = cards().calculateScore()
        if (score == other) {
            return GameResult.GameResultByPlayer(name(), GameResult.Result.DRAW)
        }
        if (score < other) {
            return GameResult.GameResultByPlayer(name(), GameResult.Result.LOSE)
        }
        return GameResult.GameResultByPlayer(name(), GameResult.Result.WIN)
    }
}
