package blackjack.domain

class Participants(private val participants: List<Participant>) {
    val elements: List<Participant>
        get() = participants.toList()

    fun receiveTwoCardsEach(deck: Deck) {
        participants.forEach { player ->
            player.receive(deck.draw(), deck.draw())
        }
    }

    fun extractGamblers(): List<Gambler> {
        return participants.filterIsInstance<Gambler>()
    }

    fun extractNames(): List<String> {
        return participants.map { participant -> participant.name }
    }

    companion object {
        fun of(dealer: Dealer, gamblers: List<Gambler>): Participants {
            val participants = listOf(dealer) + gamblers
            return Participants(participants)
        }
    }
}
