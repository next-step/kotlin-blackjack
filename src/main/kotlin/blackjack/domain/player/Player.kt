package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.game.MatchResult
import blackjack.domain.game.MatchResult.DRAW
import blackjack.domain.game.MatchResult.LOSE
import blackjack.domain.game.MatchResult.WIN

open class Player(
    val name: String,
    val hand: Hand = Hand(),
) {
    open fun canHit(): Boolean = hand.score < PLAYER_SCORE_LIMIT

    open fun hit(card: Card) = hand.add(card)

    fun matchHand(other: Player): MatchResult =
        when {
            other.isBust() -> WIN
            this.isBust() -> LOSE
            other.isBlackjack() && this.isBlackjack() -> DRAW
            other.isBlackjack() -> LOSE
            this.isBlackjack() -> WIN
            else -> compareScore(other)
        }

    private fun compareScore(other: Player): MatchResult =
        when {
            this.hand.score > other.hand.score -> WIN
            this.hand.score < other.hand.score -> LOSE
            else -> DRAW
        }

    private fun isBust(): Boolean = hand.score > PLAYER_SCORE_LIMIT

    private fun isBlackjack(): Boolean = hand.score == PLAYER_SCORE_LIMIT

    companion object {
        private const val PLAYER_SCORE_LIMIT = 21
    }
}
