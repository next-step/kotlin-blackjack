package blackjack.model

class BlackJack(private val players: List<Player>) {
    private val cardDeck = CardDeck()

    fun init() {
        players.forEach {
            it.pickCard(cardDeck.pickCard())
            it.pickCard(cardDeck.pickCard())
        }
    }

    fun race() {
        players.forEach {
            if (it.isAvailable) {
                it.pickCard(cardDeck.pickCard())
            }
        }
    }
}
