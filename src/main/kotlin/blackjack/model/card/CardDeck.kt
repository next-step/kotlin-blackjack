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

    fun popDealerCardDummy() = popCardDummy(DEALER_INIT_CARD_COUNT)

    fun popPlayerCardDummy() = popCardDummy(PLAYER_INIT_CARD_COUNT)

    private fun popCardDummy(count: Int) =
        (1..count).map { popCard() }.toCards()

    companion object {
        const val PLAYER_INIT_CARD_COUNT = 2
        const val DEALER_INIT_CARD_COUNT = 1
        private fun cardSetGenerate() = CardType.values().flatMap(::generateCardType).shuffled()
        private fun generateCardType(cardType: CardType) = CardNumber.values().map { Card(cardType, it) }
    }
}
