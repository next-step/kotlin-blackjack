package blackjack.domain

fun interface UserDrawInterface {
    fun canDraw(user: User): Boolean
}

abstract class Player(
    val name: String,
    val deck: Deck,
) {
    val finalPoint: Int by lazy { deck.sum() }

    fun getDeckSize() = deck.size

    fun addCard(card: Card) {
        deck.addCard(card)
    }

    fun calculatePoint(): Int {
        return deck.sum()
    }

    fun isBust(): Boolean {
        return deck.sum() > PointCalculator.BLACKJACK_LIMIT
    }

    abstract fun canDraw(): Boolean
}

open class User(
    name: String,
    deck: Deck,
    private val userDrawInterface: UserDrawInterface,
) : Player(name, deck) {

    init {
        require(name.isNotBlank()) { EMPTY_NAME_ERROR_MESSAGE }
    }

    override fun canDraw(): Boolean {
        return !isBust() && userDrawInterface.canDraw(this)
    }

    companion object {
        private const val EMPTY_NAME_ERROR_MESSAGE = "이름이 비어있을 수 없습니다"
    }
}

class Dealer(deck: Deck) : Player(DEALER_NAME, deck), Comparable<User> {

    override fun canDraw(): Boolean {
        return calculatePoint() <= DEALER_HIT_THRESHOLD
    }

    override fun compareTo(other: User): Int {
        return when {
            isBust() -> -1
            other.isBust() -> 1
            else -> finalPoint - other.finalPoint
        }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_HIT_THRESHOLD = 16
    }
}
