package camp.nextstep.blackjack

class Card private constructor(val suit: CardSuit, val number: CardNumber) {

    operator fun component1() = suit

    operator fun component2() = number

    companion object {

        private val CARDS = CardSuit.values()
            .associateWith { CardNumber.values() }
            .mapValues { (suit, numbers) -> numbers.map { suit.with(it) } }
            .values
            .flatten()
            .associateBy { key(it.suit, it.number) }

        fun of(suit: CardSuit, number: CardNumber): Card {
            return requireNotNull(CARDS[key(suit, number)]) { "잘못된 카드 모양과 숫자입니다." }
        }

        fun ofCombinations(): Set<Card> {
            return CARDS.values.toSet()
        }

        private fun key(suit: CardSuit, number: CardNumber): String {
            return suit.name + number.name
        }

        private fun CardSuit.with(number: CardNumber) = Card(this, number)
    }
}
