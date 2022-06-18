package blackjack.domain.participant

import blackjack.domain.card.Hand

class Player(
    name: String,
    hand: Hand = Hand.empty(),
    state: State = Hittable,
    private val bettingMoney: Money = Money.ZERO
) : Participant(name, hand, state) {

    init {
        require(bettingMoney >= Money.ZERO) { "베팅 금액은 0 이상이어야 합니다" }
    }

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

    fun match(dealer: Dealer): Match {
        val match = state.compare(dealer.state)
        when (match) {
            Match.WIN -> {
                val money = applyBonus(bettingMoney)
                earn(money)
                dealer.lost(money)
            }
            Match.LOSE -> {
                lost(bettingMoney)
                dealer.earn(bettingMoney)
            }
            Match.DRAW -> {}
        }
        return match
    }

    private fun applyBonus(money: Money): Money {
        if (hand.size() == DISTRIBUTED_CARDS_SIZE) {
            return money * BONUS_RATE
        }
        return money
    }

    companion object {
        private const val DISTRIBUTED_CARDS_SIZE = 2
        private const val BONUS_RATE = 1.5
    }
}
