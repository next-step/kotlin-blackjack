package blackjack.domain.player.state

import blackjack.domain.player.state.hands.Hands

data class Stay(override val hands: Hands) : Finish(hands) {

    override fun match(other: PlayerState): MatchResult {
        return when (other) {
            is BlackJack -> MatchResult.LOSE
            is Bust -> MatchResult.WIN
            else -> MatchResult.values(score().compareTo(other.score()))
        }
    }
}
