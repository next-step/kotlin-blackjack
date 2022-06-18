package blackjack.domain.participant

import blackjack.domain.card.Hand

class Player(
    name: String,
    hand: Hand = Hand.empty(),
    state: State = Hittable,
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
}
