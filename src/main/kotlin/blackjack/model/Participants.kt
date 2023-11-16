package blackjack.model

import blackjack.model.pack.ShuffledPack

class Participants(
    val participants: Set<Player>,
) {
    fun count(): Int {
        return participants.size
    }

    fun names(): String {
        return participants.joinToString(separator = ", ") { it.name }
    }

    fun dealing() {
        participants.forEach { it.deal(ShuffledPack.pickCard(), ShuffledPack.pickCard()) }
    }

    companion object {
        fun of(inputPlayers: String): Participants {
            return Participants(
                inputPlayers.split(",")
                    .asSequence()
                    .map { Player(it) }
                    .toSet()
            )
        }
    }
}
