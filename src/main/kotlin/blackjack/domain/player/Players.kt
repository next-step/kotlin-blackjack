package blackjack.domain.player

class Players(
    val dealer: Dealer,
    val guest: List<Player>,
) {

    fun getAllPlayers(): List<Participant> {
        return listOf(dealer) + guest
    }

    fun match() {
        dealer.match(guest)
    }
}
