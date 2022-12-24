package blackjack.domain.state

import blackjack.domain.Blackjack
import blackjack.domain.card.Cards
import blackjack.domain.player.Player
import kotlin.jvm.Throws

enum class State(
    override val isAbleToHit: Boolean,
    override val isAbleToStay: Boolean,
    val isFinished: Boolean
): PlayerAction {
    IN_PROGRESS(true, true, false) {
        override fun hit(player: Player): State = when {
            player.cards.isBlackjack() -> HIT_AND_BLACKJACK
            player.cards.isBust() -> BUST
            else -> IN_PROGRESS
        }
    },
    COMPLETED(false, false, true),
    HIT_AND_BLACKJACK(false, true, true),
    BLACKJACK(false, true, true),
    BUST(false, false, true);

    @Throws(UnsupportedOperationException::class)
    override fun hit(player: Player): State {
        throw UnsupportedOperationException("hit is not supported when status is ${this.name}")
    }

    override fun stay(player: Player): State =
        if (isFinished) this else State.finalStateOf(player.cards)

    companion object {
        fun of(cards: Cards): State =
            when (cards.score) {
                Blackjack.BLACKJACK_BEST_SCORE -> BLACKJACK
                in (1 until Blackjack.BLACKJACK_BEST_SCORE) -> IN_PROGRESS
                else -> BUST
            }

        fun finalStateOf(cards: Cards): State =
            when (cards.score) {
                Blackjack.BLACKJACK_BEST_SCORE ->
                    if (cards.size == Player.INIT_CARD_COUNT) BLACKJACK
                    else HIT_AND_BLACKJACK
                in (1 until Blackjack.BLACKJACK_BEST_SCORE) -> COMPLETED
                else -> BUST
            }
    }
}
