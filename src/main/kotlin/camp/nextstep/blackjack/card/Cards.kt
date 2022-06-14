package camp.nextstep.blackjack.card

object Cards {

    private val CARDS = CardSuit.values()
        .associateWith { CardNumber.values() }
        .mapValues { (suit, numbers) -> numbers.map { suit.with(it) } }
        .values
        .flatten()
        .associateBy { CardId(it.suit, it.number) }

    fun of(suit: CardSuit, number: CardNumber): Card {
        return requireNotNull(CARDS[CardId(suit, number)]) { "잘못된 카드 모양과 숫자입니다." }
    }

    private data class CardId(val suit: CardSuit, val number: CardNumber)

    fun ofCombinations(): Set<Card> {
        return CARDS.values.toSet()
    }

    private fun CardSuit.with(number: CardNumber) = Card(this, number)
}
