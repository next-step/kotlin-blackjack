package blackjack

class Player(
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

    fun flip(dealer: Dealer): GameResult {
        return when {
            bust() -> GameResult.LOSE
            dealer.bust() -> GameResult.WIN
            cardPoint() > dealer.cardPoint() -> GameResult.WIN
            cardPoint() < dealer.cardPoint() -> GameResult.LOSE
            else -> GameResult.TIE
        }
    }

    fun bust(): Boolean = playingCards.bust()

}
