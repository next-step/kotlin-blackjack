package blackjack.domain

class Participants(
    val dealer: Dealer,
    val players: List<Player>,
) {
    fun getAllParticipants(): List<Participant> {
        return listOf(dealer) + players
    }
}
