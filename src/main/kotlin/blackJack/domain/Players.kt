package blackJack.domain

class Players(val players: List<Player>) {
    fun giveCardAll(dealer: Dealer) {
        players.forEach { dealer.giveCard(it) }
    }
}
