package blackjack.domain

data class Participants(
    val dealer: Dealer,
    val players: Players
) {
    fun getAll(): List<Participant> {
        return listOf(dealer) + players
    }
}
