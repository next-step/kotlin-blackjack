package blackjack.domain

import blackjack.model.Card

class GamePlay(
    override val state: State = Started()
): Play {

    override val finished: Boolean
        get() = TODO("Not yet implemented")
    override val hit: Boolean
        get() = TODO("Not yet implemented")
    override val stay: Boolean
        get() = TODO("Not yet implemented")
    override val bust: Boolean
        get() = TODO("Not yet implemented")
    override val blackjack: Boolean
        get() = TODO("Not yet implemented")

    override fun shouldBeReadyToPlay(): Boolean {
        TODO("Not yet implemented")
    }

    override fun draw(card: Card) {
        TODO("Not yet implemented")
    }

    override fun stay() {
        TODO("Not yet implemented")
    }

    override fun score(): Int {
        TODO("Not yet implemented")
    }
}

interface Play {
    val state: State

    val finished: Boolean
    val hit: Boolean
    val stay: Boolean
    val bust: Boolean
    val blackjack: Boolean

    fun shouldBeReadyToPlay(): Boolean
    fun draw(card: Card)
    fun stay()
    fun score(): Int
}
