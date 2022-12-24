package blackjack.domain

import blackjack.domain.Finished.Stay
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
    override val finished: Boolean = false

    override fun draw(card: Card): State {
        TODO("Not yet implemented")
    }

    override fun stay(): State = TODO("Not yet implemented")

    private fun copy(): Started = TODO("Not yet implemented")
}

sealed interface Playing : State {
    override val finished: Boolean
        get() = false

    class Hit(override val cards: Cards = Cards()) : Playing {
        override fun draw(card: Card): State {
            TODO("Not yet implemented")
        }

        override fun stay(): State = Stay(cards)

        private fun copy(): Hit = Hit(cards)
    }
}

sealed interface Finished : State {
    override val finished: Boolean
        get() = true

    override fun draw(card: Card): State = TODO("Not yet implemented")
    override fun stay(): State = TODO("Not yet implemented")

    class Stay(override val cards: Cards = Cards()) : Finished
    class Bust(override val cards: Cards = Cards()) : Finished
    class Blackjack(override val cards: Cards = Cards()) : Finished
}
