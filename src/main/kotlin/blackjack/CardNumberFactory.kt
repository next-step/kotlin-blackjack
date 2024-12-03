package blackjack

object CardNumberFactory {
    val ACE = CardNumber.Ace
    val JACK = CardNumber.Jack
    val QUEEN = CardNumber.Queen
    val KING = CardNumber.King

    private val DEFAULT_NUMBER_RANGE = 2..10
    private val numbers = DEFAULT_NUMBER_RANGE.associateWith { Number(it) }

    fun number(value: Int): CardNumber {
        require(value in DEFAULT_NUMBER_RANGE) { "숫자 카드는 2에서 10 사이여야 합니다: value=$value" }
        return numbers.getValue(value)
    }

    fun all(): List<CardNumber> = listOf(ACE, JACK, QUEEN, KING) + numbers.values

    fun allMap(transform: (CardNumber) -> Card): List<Card> {
        return all().map(transform)
    }
}
