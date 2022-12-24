package blackjack

open class Player(
    val name: String,
    val playingCards: PlayingCards = PlayingCards(),
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
                dealer.addLosingCount()
            }

            dealer.cardPoint() > this.cardPoint() -> {
                dealer.addWinningCount()
                losingCount++
            }
        }
    }

    fun bust(): Boolean = playingCards.bust()

}
