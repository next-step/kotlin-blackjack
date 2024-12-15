package blackjack.domain

class Participants(private val participants: List<Participant>) : Collection<Participant> by participants {
    fun receiveTwoCardsEach(deck: Deck) {
        participants.forEach { player ->
            player.receive(deck.draw(), deck.draw())
        }
    }

    fun extractGamblers(): List<Gambler> {
        return participants.filterIsInstance<Gambler>()
    }

    companion object {
        fun of(dealer: Dealer, gamblers: List<Gambler>): Participants {
            val participants = listOf(dealer) + gamblers
            return Participants(participants)
        }
    }
}
