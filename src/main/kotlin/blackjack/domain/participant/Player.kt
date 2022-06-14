package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.Point

class Player(
    override val name: String,
    override val hand: Hand = Hand.empty(),
    state: State = Hittable,
) : Participant {

    override var state: State = state
        private set

    override fun isPlayable(askHit: (String) -> Boolean): Boolean {
        if (!isPlayableState()) {
            return false
        }

        val saidHit = askHit(name)

        if (!saidHit) {
            state = Stay(hand.calculate())
        }

        return saidHit
    }

    private fun isPlayableState(): Boolean {
        return state is Hittable
    }

    override fun receive(card: Card) {
        check(isPlayableState()) { "카드를 더 이상 받을 수 없는 상태입니다." }
        hand.add(card)
        changeState()
    }

    override fun match(dealer: Dealer): Match {
        return state.compare(dealer.state)
    }

    private fun changeState() {
        val point = hand.calculate()
        state = when {
            point == Point.BLACKJACK -> BlackJack
            point > Point.BLACKJACK -> Bust
            else -> Hittable
        }
    }
}
