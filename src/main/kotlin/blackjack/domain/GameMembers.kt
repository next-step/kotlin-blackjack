package blackjack.domain

import blackjack.domain.Participant.Dealer

class GameMembers(val players: List<Participant.Player>, val dealer: Dealer) {
    fun allParticipants(): Participants {
        return Participants(listOf(dealer) + players)
    }

    fun playersWithoutDealer(): Participants {
        return Participants(players)
    }
}
