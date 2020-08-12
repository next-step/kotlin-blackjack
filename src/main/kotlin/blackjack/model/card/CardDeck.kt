package blackjack.model.card

class CardDeck : Deck {
    private val cards = cardGenerate().toMutableSet()

    override fun popCard(): Card {
        require(cards.isNotEmpty()) { "게임 무효 : Card가 모두 소모되었습니다." }
        return cards.first().also {
            cards.remove(it)
        }
    }

    private fun cardGenerate() = CardType.values().flatMap(::generateCardType).shuffled()

    private fun generateCardType(cardType: CardType) = CardNumber.values().map { Card(cardType, it) }
}

interface Deck {
    fun popCard(): Card

    fun popTwoCard() =
        Cards().apply {
            addCard(popCard())
            addCard(popCard())
        }
}
