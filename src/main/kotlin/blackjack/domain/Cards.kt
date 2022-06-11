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
        var score = hands.filter { it.number != CardNumber.ACE }.sumOf { it.number.score }
        repeat(hands.count { it.number == CardNumber.ACE }) {
            score += processAce(score)
        }
        return score
    }

    private fun processAce(score: Int): Int {
        return if (score > BLACKJACK_SCORE - CardNumber.ACE.highScore)
            CardNumber.ACE.score
        else
            CardNumber.ACE.highScore
    }

    fun isBust(): Boolean {
        return getScore() > BLACKJACK_SCORE
    }

    override fun toString(): String {
        return hands.joinToString(", ")
    }

    companion object {
        const val BLACKJACK_SCORE = 21
    }
}
