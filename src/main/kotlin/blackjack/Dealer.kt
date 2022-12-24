package blackjack

class Dealer(playingCards: PlayingCards = PlayingCards()) {
    private val DEALER_MIN_POINT = Point(17)
    val player = Player("딜러", playingCards)

    fun hitUntil(deck: CardDeck): Int {
        var count = 0
        while (cardPoint() < DEALER_MIN_POINT && !bust()) {
            player.addCard(deck.deal())
            count++
        }
        return count
    }

    fun cardPoint(): Point = player.cardPoint()

    private fun bust(): Boolean = player.bust()
    fun addLosingCount() {
        player.losingCount++
    }

    fun addWinningCount() {
        player.winningCount++
    }

    fun addCard(deal: Card) {
        player.addCard(deal)
    }
}

