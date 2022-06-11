package blackjack.domain

/**
 * 카드르 저장하는 클래스
 * Created by Jaesungchi on 2022.06.07..
 */
class Cards {
    private val hands = mutableListOf<Card>()

    fun addCard(type: String? = null, number: String? = null) {
        hands.add(Card.take(type, number))
    }

    fun getSize() = hands.size

    fun getScore(): Int {
        return if (getHighScore() <= BLACKJACK_SCORE) {
            getHighScore()
        } else {
            getLowScore()
        }
    }

    private fun getHighScore(): Int {
        return hands.sumOf { it.number.highScore }
    }

    private fun getLowScore(): Int {
        return hands.sumOf { it.number.lowScore }
    }

    fun isOverScore(): Boolean {
        return getScore() > 21
    }

    override fun toString(): String {
        return hands.joinToString(", ")
    }

    companion object {
        const val BLACKJACK_SCORE = 21
    }
}
