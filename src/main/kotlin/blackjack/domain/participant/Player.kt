package blackjack.domain.participant

import blackjack.domain.card.Hand

class Player(
    name: String,
    hand: Hand = Hand.empty(),
    state: State = Hittable,
    val bettingMoney: Money = Money.ZERO
) : Participant(name, hand, state) {

    init {
        require(bettingMoney >= Money.ZERO) { "베팅 금액은 0 이상이어야 합니다" }
    }

    override fun isPlayable(askHit: (String) -> Boolean): Boolean {
        if (!isPlayableState()) {
            return false
        }

        return askHit(name).also {
            if (!it) state = Stay(hand.calculate())
        }
    }

    private fun isPlayableState(): Boolean {
        return state is Hittable
    }

    fun match(dealer: Dealer): Match {
        return state.compare(dealer.state)
    }
}
