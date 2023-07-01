package blackjack.domain

data class Players(
    val values: List<Player>
) {
    fun receiveCards(dealer: Dealer): Players {
        return values.map { player -> player.receiveCards(dealer.dealInitialCard()) }
            .let { Players(it) }
    }
}