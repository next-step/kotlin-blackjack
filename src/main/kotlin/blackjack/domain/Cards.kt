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
        var score = hands.sumOf { it.number.score }
        if (hands.any { it.number == CardNumber.ACE } && score + ACE_SUB_SCORE <= BLACKJACK_SCORE)
            score += ACE_SUB_SCORE
        return score
    }

    fun isBust(): Boolean {
        return getScore() > BLACKJACK_SCORE
    }

    override fun toString(): String {
        return hands.joinToString(", ")
    }

    companion object {
        const val BLACKJACK_SCORE = 21
        const val ACE_SUB_SCORE = 10
    }
}
