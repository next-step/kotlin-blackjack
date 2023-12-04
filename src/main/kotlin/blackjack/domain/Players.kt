package blackjack.domain

class Players(private val players: List<Player>) {

    fun getFirstTwoCards(cardDeck: Deck, callback: (Player) -> Unit) {
        for (player in players) {
            val cards = cardDeck.firstDraw()
            player.getFirstDealCards(cards)
            callback(player)
        }
    }
}
