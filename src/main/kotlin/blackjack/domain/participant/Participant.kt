package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.Point

abstract class Participant(
    val name: String,
    val hand: Hand,
    state: State = Hittable
) : Gambler() {
    var state: State = state
        protected set

    fun point(): Point = hand.calculate()

    abstract fun isPlayable(askHit: (String) -> Boolean): Boolean

    fun receive(card: Card) {
        check(isPlayableState()) { "카드를 더 이상 받을 수 없는 상태입니다." }
        hand.add(card)
        changeState()
    }

    protected open fun changeState() {
        val point = hand.calculate()
        state = when {
            point == Point.BLACKJACK -> BlackJack
            point > Point.BLACKJACK -> Bust
            else -> Hittable
        }
    }

    private fun isPlayableState(): Boolean = state is Hittable

    open fun open(): Hand = hand
}

enum class Match {
    WIN, LOSE, DRAW
}
