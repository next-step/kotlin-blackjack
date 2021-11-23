package blackjack.domain.player.state

import blackjack.domain.player.state.hands.Hands

data class Bust(override val hands: Hands) : Finish(hands) {

    override fun match(other: PlayerState): MatchResult {
        if (other is Bust) {
            return MatchResult.DRAW
        }
        return MatchResult.LOSE
    }
}
