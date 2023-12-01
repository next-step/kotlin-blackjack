package blackjack.domain

class Players(private val players: List<Player>) {

    fun getFirstTwoCards(cards: List<Card>, callback: (Player) -> Unit) {
        for (player in players) {
            player.getFirstDealCards(cards)
            callback(player)
        }
    }
}
