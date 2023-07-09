package blackjack.domain

class Participants(val participants: List<Participant>) {
    operator fun plus(other: Participants): Participants {
        return Participants(participants + other.participants)
    }

    fun forEach(action: (Participant) -> Unit) {
        participants.forEach(action)
    }

    fun toList(): List<Participant> {
        return participants.toList()
    }

    fun filter(predicate: (Participant) -> Boolean): Participants {
        return Participants(participants.filter(predicate))
    }

    fun getDealer(): Dealer {
        return participants.filterIsInstance<Dealer>().first()
    }

    fun getPlayers(): List<Player> {
        return participants.filterIsInstance<Player>()
    }

    fun isDealerBust(): Boolean {
        return getDealer().isBust()
    }

    fun isDealerLose(): Boolean {
        return getDealer().isBust()
    }
}
