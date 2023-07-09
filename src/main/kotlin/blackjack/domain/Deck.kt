package blackjack.domain

class Deck private constructor(
    val cards: Cards
) {
    private var index = INIT_INDEX

    fun pop(input: Int = index): Card {
        require(input <= 52) { "카드의 인덱스는 52가 넘을 수 없습니다." }
        return cards.get(index++)
    }

    companion object {

        private const val INIT_INDEX = 0

        fun create(): Deck {
            return Deck(shuffledCards())
        }

        private fun shuffledCards(): Cards {
            val cards = Denomination.values().flatMap { denomination ->
                CardType.values().map { cardType ->
                    Card(cardType, denomination)
                }
            }
            return Cards.of(cards)
        }
    }
}
