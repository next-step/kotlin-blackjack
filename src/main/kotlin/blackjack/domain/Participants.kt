package blackjack.domain

data class Participants(val members: List<Participant>) {
    fun dealer(): Participant.Dealer {
        return members.filterIsInstance<Participant.Dealer>().firstOrNull()
            ?: throw IllegalStateException("No dealer found in participants")
    }

    fun players(): List<Participant.Player> {
        return members.filterIsInstance<Participant.Player>()
    }
}
