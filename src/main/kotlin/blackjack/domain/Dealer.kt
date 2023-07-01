package blackjack.domain

class Dealer(
    private val cardGenerator: CardGenerator = RandomCardGenerator()
) {
    fun dealInitialCard(): List<Card> {
        return List(Player.DEFAULT_CARD_SIZE) { dealCard() }.toList()
    }

    fun dealCard(): Card {
        return cardGenerator.generate()
    }
}
