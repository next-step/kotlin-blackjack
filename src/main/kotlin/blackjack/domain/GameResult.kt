package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.player.Participant

data class GameResult(
    val playerName: String,
    val cards: Set<Card>,
    val score: Int,
) {
    companion object {
        fun from(participant: Participant): GameResult =
            GameResult(playerName = participant.name(), cards = participant.cards(), score = participant.score())
    }
}
