package blackjack.domain

class Dealer(
    private val cardGenerator: CardGenerator = randomCardGenerator,
    cards: Cards = Cards()
) {
    var cards: Cards = cards
        private set

    fun receiveDefaultCards() {
        this.cards = Cards(dealDefaultCard())
    }

    fun dealDefaultCard(): List<Card> {
        return List(Player.DEFAULT_CARD_SIZE) { dealCard() }.toList()
    }

    fun dealCard(): Card {
        return cardGenerator.generate()
    }
}
