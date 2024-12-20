package blackjack.domain

import blackjack.domain.Participant.Dealer

class GameMembers(private val players: List<Participant.Player>, private val dealer: Dealer) {
    fun allParticipants(): Participants {
        return Participants(listOf(dealer) + players)
    }

    fun playersWithoutDealer(): Participants {
        return Participants(players)
    }

    fun dealer(): Dealer {
        return dealer
    }
}
