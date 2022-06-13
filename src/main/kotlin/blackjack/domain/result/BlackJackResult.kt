package blackjack.domain.result

import blackjack.domain.participant.Match

data class BlackJackResult(
    val dealerResult: DealerResult,
    val playerResults: List<PlayerResult>
)

data class DealerResult(
    val win: Int,
    val draw: Int,
    val lose: Int
)

data class PlayerResult(
    val playerName: String,
    val match: Match
)
