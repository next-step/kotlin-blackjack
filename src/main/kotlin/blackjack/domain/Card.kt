package blackjack.domain

class Card(val number: CardNumber, val shape: CardShape) {
    override fun toString(): String = number.value + shape.label

    companion object {
        val CARD_DECK = CardShape.values().flatMap { shape ->
            CardNumber.values().map { number -> Card(number, shape) }
        }

        fun of(number: CardNumber, shape: CardShape): Card {
            return CARD_DECK.asSequence()
                .filter { it.shape == shape }
                .find { it.number == number }
                ?: throw IllegalArgumentException("찾을 수 없는 카드입니다.")
        }
    }
}
