package blackjack.domain.card

fun interface CardDrawer {
    fun getCard(): Card
}

data class Card(val suit: Suit, val cardNumber: CardNumber)

class Cards(cardList: List<Card>) : Iterable<Card> {

    private val cardList: MutableList<Card> = cardList.toMutableList()

    val size: Int
        get() = cardList.size

    var score: Int = calculateScore()
        private set

    fun addCard(card: Card) {
        cardList.add(card)
        score = calculateScore()
    }

    private fun calculateScore(): Int {
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

    fun isBust(): Boolean {
        return score > BLACKJACK_LIMIT
    }

    fun isBlackjack(): Boolean {
        return cardList.size == INITIAL_CARDS_SIZE && score == BLACKJACK_LIMIT
    }

    companion object {
        private const val EMPTY_CARDS_ERROR_MESSAGE = "남아있는 카드가 없습니다"
        const val BLACKJACK_LIMIT = 21
        private const val ACE_BONUS_SCORE = 10
        private const val INITIAL_CARDS_SIZE = 2

        fun getInitCards(cardDrawer: CardDrawer): Cards {
            val cardList = List(INITIAL_CARDS_SIZE) { cardDrawer.getCard() }
            return Cards(cardList)
        }
    }
}

enum class Suit {
    SPADE, HEART, DIAMOND, CLOVER
}
