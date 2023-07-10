package blackjack.domain

class Dealer(
    private val cardGenerator: CardGenerator = randomCardGenerator,
    val name: String = DEALER_NAME,
    cards: Cards = Cards()
) : Participant {
    var cards: Cards = cards
        private set

    val score
        get() = ScoreCalculator.calculateScore(cards)

    override fun receiveCard(newCard: Card) {
        cards = Cards(cards.values + newCard)
    }

    override fun receiveCards(newCards: List<Card>) {
        this.cards = Cards(newCards)
    }

    fun dealDefaultCard(): List<Card> {
        return List(Player.DEFAULT_CARD_SIZE) { dealCard() }.toList()
    }

    fun dealCard(): Card {
        return cardGenerator.generate()
    }

    companion object {
        const val DEALER_NAME = "딜러"
    }
}
