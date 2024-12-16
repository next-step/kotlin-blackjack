package blackjack.domain

import blackjack.domain.Participant.Dealer

data class Participants(private val players: List<Participant>) {
    constructor(dealer: Dealer, participant: Participants) : this(listOf(dealer) + participant.players)

    fun allPlayers(): List<Participant> = players

    fun forEach(action: (Participant) -> Unit) = players.forEach(action)
}
