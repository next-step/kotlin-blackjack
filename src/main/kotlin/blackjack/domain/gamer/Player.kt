package blackjack.domain.gamer

import blackjack.domain.game.MatchResultType
import blackjack.domain.game.PlayerMatchResult

class Player(
    val name: PlayerName,
) : Gamer() {

    override fun canHit(): Boolean {
        return state.isHit()
    }

    fun match(dealer: Dealer): PlayerMatchResult {
        val matchResultType = when {
            dealer.state.isBust() -> MatchResultType.WIN
            state.isBust() -> MatchResultType.LOSE
            dealer.state.cards.score.value < state.cards.score.value -> MatchResultType.WIN
            dealer.state.cards.score.value == state.cards.score.value -> MatchResultType.TIE
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
            cards = state.cards,
        )
    }
}

fun List<Player>.captureAllCards(): List<PlayerCards> {
    return map { it.captureCards() }
}
