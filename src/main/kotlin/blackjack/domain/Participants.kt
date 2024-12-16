package blackjack.domain

import blackjack.domain.Participant.Player

data class Participants(private val players: List<Player>) {
    constructor(participants: Participants, dealer: Dealer) : this(listOf(dealer) + participants.players)

    fun allPlayers(): List<Player> = players

    fun forEach(action: (Player) -> Unit) = players.forEach(action)
}
