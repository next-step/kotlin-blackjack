package blackjack.domain

/**
 * 카드르 저장하는 클래스
 * Created by Jaesungchi on 2022.06.07..
 */
class Cards {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards.toList()

    fun addCard() {
        _cards.add(Card.take())
    }

    fun getScore(): Int {
        return if (getHighScore() <= 21) {
            getHighScore()
        } else {
            getLowScore()
        }
    }

    private fun getHighScore(): Int {
        return _cards.sumOf { it.number.lowScore }
    }

    private fun getLowScore(): Int {
        return _cards.sumOf { it.number.highScore }
    }

    fun isOverScore(): Boolean {
        return getScore() > 21
    }

    override fun toString(): String {
        return cards.joinToString(", ")
    }
}
