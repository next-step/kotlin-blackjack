package blackjack.domain.player.state

import blackjack.domain.player.state.hands.Hands

data class Stay(override val hands: Hands) : Finish(hands) {

    override fun match(other: PlayerState): MatchResult {
        val hands = other.hands
        return when {
            hands.isBlackJack() -> MatchResult.LOSE
            hands.isBust() -> MatchResult.WIN
            else -> MatchResult.from(score().compareTo(other.score()))
        }
    }
}
