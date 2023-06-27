package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.InitCard

abstract class GamerState {

    abstract val cards: Cards

    fun isInit(): Boolean {
        return isHit() && cards.size == InitCard.INIT_CARD_SIZE
    }

    fun isHit(): Boolean {
        return this is Hit
    }

    fun isBust(): Boolean {
        return this is Bust
    }

    open fun init(initCard: InitCard): GamerState {
        throw IllegalStateException("not support init")
    }

    open fun hit(card: Card): GamerState {
        throw IllegalStateException("not support hit")
    }

    open fun stay(): GamerState {
        throw IllegalStateException("not support stay")
    }

    companion object {

        fun wait(): GamerState {
            return Wait()
        }
    }
}
