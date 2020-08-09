package blackjack.model.card

class CardDeck : Deck {
    private val _cards = cardGenerate().toMutableSet()
    val cards: Set<Card>
        get() = _cards

    override fun popCard(): Card {
        require(_cards.size > 0) { "게임 무효 : Card가 모두 소모되었습니다." }
        return _cards.first().also {
            _cards.remove(it)
        }
    }

    private fun cardGenerate() = CardType.values().flatMap(::generateCardType).shuffled()

    private fun generateCardType(cardType: CardType) = CardNumber.values().map { Card.newInstance(cardType, it) }
}

interface Deck {
    fun popCard(): Card
}
