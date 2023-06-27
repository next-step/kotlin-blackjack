package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.gamestate.Competition
import blackjack.domain.player.Participant

data class GameResult(
    val dealerGameResult: DealerGameResult,
    val playerGameResults: List<PlayerGameResult>,
)

data class PlayerGameResult(
    val playerName: String,
    val cards: Set<Card>,
    val score: Int,
    val competition: Competition,
) {
    companion object {
        fun of(participant: Participant, competition: Competition) = PlayerGameResult(
            playerName = participant.name(),
            cards = participant.cards(),
            score = participant.score(),
            competition = competition,
        )
    }
}

data class DealerGameResult(
    val playerName: String,
    val cards: Set<Card>,
    val score: Int,
    val competitions: Map<Competition, Int>,
) {
    companion object {
        fun of(participant: Participant, competitions: Map<Competition, Int>) = DealerGameResult(
            playerName = participant.name(),
            cards = participant.cards(),
            score = participant.score(),
            competitions = competitions,
        )
    }
}
