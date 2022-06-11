package blackjack.domain

import blackjack.domain.State.BLACKJACK
import blackjack.domain.State.BUST
import blackjack.domain.State.HITTABLE
import blackjack.domain.State.STAY

class Player(
    override val name: String,
    override val hand: Hand = Hand.empty(),
    state: State = HITTABLE,
    private val selectHit: (String) -> Boolean = { true }
) : Participant {

    override var state: State = state
        private set

    override fun saidHit(): Boolean {
        val isHit = selectHit(name)
        if (!isHit) {
            state = STAY
        }
        return isHit
    }

    override fun receive(card: Card) {
        check(state.canPlay) { "카드를 더 이상 받을 수 없는 상태입니다." }
        hand.add(card)
        changeState()
    }

    private fun changeState() {
        val point = hand.calculate()
        when {
            point == Point.BLACKJACK -> state = BLACKJACK
            point > Point.BLACKJACK -> state = BUST
        }
    }
}
