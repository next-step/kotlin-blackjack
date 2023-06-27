package blackjack.domain.gamer

import blackjack.domain.game.MatchResultType
import blackjack.domain.game.PlayerMatchResult

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

    fun captureCards(): PlayerCards {
        return PlayerCards(
            playerName = name,
            cards = cards,
        )
    }
}

fun List<Player>.captureAllCards(): List<PlayerCards> {
    return map { it.captureCards() }
}
