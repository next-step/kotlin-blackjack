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
    val earningMoney: Long,
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
    val earningMoney: Long,
) : ParticipantResult(
    name = name,
    score = score,
    cards = cards,
)
