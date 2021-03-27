package blackjack.dto

import blackjack.domain.player.Dealer
import blackjack.domain.player.MatchingResult
import blackjack.domain.player.Player

data class ResultsDto(
    val dealer: DealerResult,
    val players: List<PlayerResult>
) {
    constructor(dealer: Dealer, players: List<Pair<Player, MatchingResult>>) : this(
        DealerResult(dealer, players.map { it.second }),
        players.map { PlayerResult(it.first, it.second) }
    )
}

data class DealerResult(
    val cards: List<String>,
    val score: Int,
    val winCount: Int,
    val drawCount: Int,
    val loseCount: Int
) {
    constructor(dealer: Dealer, playersResult: List<MatchingResult>) : this(
        cards = dealer.state.cards.toView(),
        score = dealer.score.value,
        winCount = playersResult.filter { it == MatchingResult.LOSE }.count(),
        drawCount = playersResult.filter { it == MatchingResult.DRAW }.count(),
        loseCount = playersResult.filter { it == MatchingResult.WIN }.count()
    )
}

data class PlayerResult(
    val name: String,
    val cards: List<String>,
    val score: Int,
    val matchResult: String
) {
    constructor(player: Player, matchResult: MatchingResult) : this(
        player.name,
        player.state.cards.toView(),
        player.score.value,
        matchResult.toView()
    )
}

private fun MatchingResult.toView(): String {
    return when (this) {
        MatchingResult.WIN -> "승"
        MatchingResult.DRAW -> "무"
        MatchingResult.LOSE -> "패"
    }
}
