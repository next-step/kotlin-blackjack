package blackjack.domain.player

import blackjack.domain.game.PlayerMatchResult
import blackjack.domain.game.MatchResultType

class Player(
    val name: PlayerName,
) : Gamer() {

    fun match(dealer: Dealer): PlayerMatchResult {
        val matchResultType = when {
            state.isBust() -> MatchResultType.LOSE
            dealer.state.isBust() -> MatchResultType.WIN
            dealer.score.value < score.value -> MatchResultType.WIN
            dealer.score.value == score.value -> MatchResultType.TIE
            else -> MatchResultType.LOSE
        }
        return PlayerMatchResult(
            playerName = name,
            matchResultType = matchResultType,
        )
    }
}
