package blackjack.model

class Players(val players: List<Player>) {

    fun noMoreHit(): Boolean = !(players.any { it.hit })

    fun initialCardDealing(dealer: Dealer) {
        players.forEach {
            it.addCards(dealer.dealingTwoCards())
        }
    }

    fun hitablePlayers(): List<Player> =
        players.filter { it.hit }
}
