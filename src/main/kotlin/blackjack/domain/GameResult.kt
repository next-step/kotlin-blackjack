package blackjack.domain

data class GameResult(
    val dealerResult: DealerResult,
    val playerResults: List<PlayerResult>,
)

sealed class ParticipantResult(
    val name: String,
    val score: Int,
    val cards: List<Card>,
)

class DealerResult(
    name: String,
    score: Int,
    cards: List<Card>,
    val winCount: Int,
    val loseCount: Int,
) : ParticipantResult(
    name = name,
    score = score,
    cards = cards,
)

class PlayerResult(
    name: String,
    score: Int,
    cards: List<Card>,
    val isWinner: Boolean,
) : ParticipantResult(
    name = name,
    score = score,
    cards = cards,
)
