package blackjack.domain

/**
 * 카드르 저장하는 클래스
 * Created by Jaesungchi on 2022.06.07..
 */
class Cards {
    private val _hands = mutableListOf<Card>()
    val hands: List<Card>
        get() = _hands.toList()

    fun addCard(card: Card) {
        _hands.add(card)
    }

    fun getSize() = _hands.size

    fun getScore(): Score {
        return Score.of(this)
    }

    fun isBust(): Boolean {
        return getScore().value > BLACKJACK_SCORE
    }

    companion object {
        const val BLACKJACK_SCORE = 21
    }
}
