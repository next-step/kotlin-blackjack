package blackjack.model.card

class CardDeck(
    private val cards: MutableSet<Card> = cardSetGenerate().toMutableSet()
) {

    fun popCard(): Card {
        require(cards.isNotEmpty()) { "게임 무효 : Card가 모두 소모되었습니다." }
        return cards.first().also {
            cards.remove(it)
        }
    }

    fun popTwoCard() =
        Cards().apply {
            addCard(popCard())
            addCard(popCard())
        }

    companion object {
        private fun cardSetGenerate() = CardType.values().flatMap(::generateCardType).shuffled()

        private fun generateCardType(cardType: CardType) = CardNumber.values().map { Card(cardType, it) }
    }
}
