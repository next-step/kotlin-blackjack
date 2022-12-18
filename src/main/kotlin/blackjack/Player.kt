package blackjack

open class Player(
    val name: String,
    val playingCards: PlayingCards = PlayingCards(),
    var winningCount: Int = 0,
    var losingCount: Int = 0,
) {
    fun addCard(deal: Card): Player {
        playingCards.addOne(deal)
        return this
    }

    fun addCard(cards: Set<Card>): Player {
        playingCards.addAll(cards)
        return this
    }

    fun cardPoint() = playingCards.calculatePoint()

    fun flip(dealer: Dealer) {
        when {
            this.cardPoint() > dealer.cardPoint() -> {
                winningCount++
                dealer.losingCount++
            }

            dealer.cardPoint() > this.cardPoint() -> {
                dealer.winningCount++
                losingCount++
            }
        }
    }
}
