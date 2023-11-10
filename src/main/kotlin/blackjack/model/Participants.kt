package blackjack.model

class Participants(
    val participants: Set<Player>,
    // val dealer: Dealer,
) {
    fun count(): Int {
        return participants.size
    }

    fun names(): String {
        return participants.joinToString(separator = ", ") { it.name }
    }

    fun dealing() {
        participants.forEach { it.deal(Pack.anyCard(), Pack.anyCard()) }
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
