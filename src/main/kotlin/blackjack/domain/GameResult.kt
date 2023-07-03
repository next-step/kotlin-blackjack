package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.player.Participant

data class GameResult(
    val dealerGameResult: DealerGameResult,
    val playerGameResults: List<PlayerGameResult>,
)

data class PlayerGameResult(
    val playerName: String,
    val cards: Set<Card>,
    val score: Int,
    val profit: Int,
) {
    companion object {
        fun of(participant: Participant, profit: Int) = PlayerGameResult(
            playerName = participant.name(),
            cards = participant.cards(),
            score = participant.score(),
            profit = profit,
        )
    }
}

data class DealerGameResult(
    val cards: Set<Card>,
    val score: Int,
    val profit: Int,
) {
    companion object {
        fun of(participant: Participant, profit: Int) = DealerGameResult(
            cards = participant.cards(),
            score = participant.score(),
            profit = profit,
        )
    }
}
