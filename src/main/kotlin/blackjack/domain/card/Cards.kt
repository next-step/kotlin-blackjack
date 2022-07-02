package blackjack.domain.card

import blackjack.domain.Score
import blackjack.domain.player.Users

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

    fun isBlackJack(): Boolean {
        return _hands.size == Users.INIT_CARD_SIZE &&
            getScore().isBlackJackScore()
    }

    fun getSize() = _hands.size

    fun getScore(): Score {
        var score = _hands.sumOf { it.score }
        if (canAddAceSubScore(score))
            score += Score.ACE_SUB_SCORE
        return Score(score)
    }

    private fun canAddAceSubScore(score: Int): Boolean {
        return hasAce() && score + Score.ACE_SUB_SCORE <= Score.BLACKJACK_SCORE
    }

    private fun hasAce(): Boolean {
        return _hands.any { it is Ace }
    }
}
