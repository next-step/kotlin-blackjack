package blackjack.domain

class GameMembers(private val participants: Participants, private val dealer: Dealer) {
    fun allPlayers(): Participants = Participants(participants, dealer)

    fun playersWithoutDealer(): Participants = participants

    fun dealer(): Dealer = dealer
}
