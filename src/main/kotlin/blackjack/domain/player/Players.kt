package blackjack.domain.player

class Players(
    val dealer: Dealer,
    val players: List<Participant>,
) {

    fun getAllPlayers(): List<Participant> {
        return listOf(dealer) + players
    }
}
