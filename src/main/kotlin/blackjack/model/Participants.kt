package blackjack.model

import blackjack.model.pack.ShuffledPack

class Participants(
    val participants: Set<Player>,
) {
    fun count(): Int {
        return participants.size
    }

    fun dealing() {
        participants.forEach { it.deal(ShuffledPack.pickCard(), ShuffledPack.pickCard()) }
    }
}