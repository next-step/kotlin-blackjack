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
    fun blackJack(): Boolean = player.blackJack()
    fun bust(): Boolean = player.bust()

    fun addCard(card: Card) {
        player.addCard(card)
    }

    fun addCard(cards: Set<Card>) {
        player.addCard(cards)
    }

    fun firstCard(): Set<Card> = player.firstCard()
}
