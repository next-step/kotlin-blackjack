package blackjack.domain

data class Player(
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
    fun blackJack() = playingCards.cards.size == 2 && playingCards.calculatePoint() == Point.BLACK_JACK

    fun flip(dealer: Dealer): GameResult {
        return when {
            bust() -> GameResult.LOSE
            dealer.bust() -> GameResult.WIN
            blackJack() && !dealer.blackJack() -> GameResult.WIN
            cardPoint() > dealer.cardPoint() -> GameResult.WIN
            cardPoint() < dealer.cardPoint() -> GameResult.LOSE
            else -> GameResult.TIE
        }
    }

    fun bust(): Boolean = playingCards.bust()
    fun firstCard(): Set<Card> = playingCards.firstCard()

}
