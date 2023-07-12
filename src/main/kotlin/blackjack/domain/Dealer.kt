package blackjack.domain

class Dealer(
    private val cardGenerator: CardGenerator = randomCardGenerator,
    val name: String = DEALER_NAME,
    val cards: Cards = Cards()
) : Participant {

    val score
        get() = ScoreCalculator.calculateScore(cards)

    override fun receiveCard(newCard: Card) {
        cards.add(newCard)
    }

    override fun receiveCards(newCards: List<Card>) {
        cards.addAll(newCards)
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
