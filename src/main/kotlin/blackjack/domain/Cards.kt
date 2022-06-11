package blackjack.domain

/**
 * 카드르 저장하는 클래스
 * Created by Jaesungchi on 2022.06.07..
 */
class Cards {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card> // TODO 이건 없어도 될듯?
        get() = _cards.toList()

    fun addCard(type: String? = null, number: String? = null) {
        _cards.add(Card.take(type, number))
    }

    fun getScore(): Int {
        return if (getHighScore() <= 21) {
            getHighScore()
        } else {
            getLowScore()
        }
    }

    private fun getHighScore(): Int {
        return _cards.sumOf { it.number.highScore }
    }

    private fun getLowScore(): Int {
        return _cards.sumOf { it.number.lowScore }
    }

    fun isOverScore(): Boolean {
        return getScore() > 21
    }

    override fun toString(): String {
        return _cards.joinToString(", ")
    }
}
