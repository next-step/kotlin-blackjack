package blackjack.domain

import blackjack.domain.Finished.Blackjack
import blackjack.domain.Finished.Bust
import blackjack.domain.Finished.Stay
import blackjack.domain.Game.Companion.INITIAL_CARDS_COUNT
import blackjack.domain.Playing.Hit
import blackjack.model.Card

sealed interface State {
    val cards: Cards
    val finished: Boolean

    fun draw(card: Card): State
    fun stay(): State
}

class Started(
    override val cards: Cards = Cards()
) : State {
    override val finished: Boolean
        get() = false

    override fun stay(): State = Stay(cards)
    override fun draw(card: Card): State {
        cards.add(card)
        return when {
            cards.size == INITIAL_CARDS_COUNT && cards.sum() == BLACKJACK_SCORE -> Blackjack(cards)
            cards.size == INITIAL_CARDS_COUNT -> Hit(cards)
            else -> Started(cards)
        }
    }
}

sealed interface Playing : State {
    override val finished: Boolean
        get() = false

    class Hit(override val cards: Cards = Cards()) : Playing {
        override fun draw(card: Card): State {
            cards.add(card)
            return if (cards.sum() > BLACKJACK_SCORE) Bust(cards) else Hit(cards)
        }

        override fun stay(): State = Stay(cards)
    }
}

sealed interface Finished : State {
    override val finished: Boolean
        get() = true

    override fun draw(card: Card): State = throw IllegalStateException("이미 끝난 게임입니다.")
    override fun stay(): State = throw IllegalStateException("이미 끝난 게임입니다.")

    class Stay(override val cards: Cards = Cards()) : Finished
    class Bust(override val cards: Cards = Cards()) : Finished
    class Blackjack(override val cards: Cards = Cards()) : Finished
}
