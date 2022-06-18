package blackjack.domain.participant

import blackjack.domain.card.Hand

class Player(
    name: String,
    hand: Hand = Hand.empty(),
    state: State = Hittable,
    private val bettingMoney: Money = Money.ZERO
) : Participant(name, hand, state) {

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
                earn(bettingMoney)
                dealer.lost(bettingMoney)
            }
            Match.LOSE -> {}
            Match.DRAW -> {}
        }
        return match
    }

    companion object {
        private const val DISTRIBUTED_CARDS_SIZE = 2
        private const val BONUS_RATE = 1.5
    }
}
