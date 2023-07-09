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
}
