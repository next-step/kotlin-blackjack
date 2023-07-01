package blackjack.domain.card

data class Card(val suit: Suit, val cardNumber: CardNumber)

class Cards(cardList: List<Card>) : Iterable<Card> {

    private val cardList: MutableList<Card> = cardList.toMutableList()

    val size: Int
        get() = cardList.size

    fun addCard(card: Card) {
        cardList.add(card)
    }

    fun score(): Int {
        var sum = cardList.sumOf { it.cardNumber.score }
        if (hasAce() && sum + ACE_BONUS_SCORE <= BLACKJACK_LIMIT) {
            sum += ACE_BONUS_SCORE
        }
        return sum
    }

    private fun hasAce(): Boolean {
        return cardList.any { it.cardNumber == CardNumber.ACE }
    }

    fun isNotEmpty(): Boolean = cardList.isNotEmpty()

    fun drawCard(): Card = checkNotNull(cardList.removeFirstOrNull()) { EMPTY_CARDS_ERROR_MESSAGE }

    override fun iterator(): Iterator<Card> {
        return cardList.iterator()
    }

    companion object {
        private const val EMPTY_CARDS_ERROR_MESSAGE = "남아있는 카드가 없습니다"
        const val BLACKJACK_LIMIT = 21
        private const val ACE_BONUS_SCORE = 10
    }
}

enum class Suit {
    SPADE, HEART, DIAMOND, CLOVER
}
