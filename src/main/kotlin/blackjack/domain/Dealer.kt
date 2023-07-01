package blackjack.domain

class Dealer(
    private val cardGenerator: CardGenerator = RandomCardGenerator()
) {
    fun dealCard(): Card {
        return cardGenerator.generate()
    }

}