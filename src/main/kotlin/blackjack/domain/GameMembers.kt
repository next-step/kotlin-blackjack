package blackjack.domain

import blackjack.domain.Participant.Dealer

class GameMembers(private val participants: Participants, private val dealer: Dealer) {
    fun allParticipants(): Participants = Participants(dealer, participants)

    fun playersWithoutDealer(): Participants = participants

    fun dealer(): Dealer = dealer
}
