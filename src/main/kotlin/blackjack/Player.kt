package blackjack

class Player(val name: String) {
    val playerCards: PlayerCards = PlayerCards()

    val isBusted get() = playerCards.isBusted()

    fun getInitialCards(cards: List<PlayingCard>) {
        for (card in cards) {
            playerCards.addCard(card)
        }
    }

    fun hit(card: PlayingCard) {
        playerCards.addCard(card)
    }
}
