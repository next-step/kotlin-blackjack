package blackjack

import kotlin.random.Random

class Deck(private val cards: MutableList<Card> = CARDS.toMutableList()) {
    init {
        require(cards.size <= MAXIMUM_CARD_COUNT) {
            "총 카드 수는 ${MAXIMUM_CARD_COUNT}장을 초과할 수 없습니다: card.size=${cards.size}"
        }
    }

    fun draw(): Card = cards.removeFirst()

    fun size(): Int = cards.size

    fun shuffle(seed: Long? = null) {
        val random = seed?.let { Random(it) } ?: Random.Default
        cards.shuffle(random)
    }

    companion object {
        private const val MAXIMUM_CARD_COUNT = 52
        private val CARDS: List<Card> =
            Suit.entries.flatMap { suit ->
                CardNumber.entries.map { cardNumber ->
                    Card(cardNumber, suit)
                }
            }
    }
}
