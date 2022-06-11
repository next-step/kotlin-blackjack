package blackjack.domain

import blackjack.domain.State.HITTABLE

class Player(
    override val name: String,
    override val hand: Hand = Hand.empty(),
    override val state: State = HITTABLE,
    val selectHit: (String) -> Boolean = { true }
) : Participant {

    override fun receive(card: Card) {
        check(state.canPlay) { "카드를 더 이상 받을 수 없는 상태입니다." }
        hand.add(card)
    }
}
