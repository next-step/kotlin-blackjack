package study.blackjack.model

import study.blackjack.model.Match.Companion.BLACKJACK

/**
 * @author 이상준
 */
data class Cards(
    private val cards: List<Card>,
) {
    fun size(): Int = cards.size

    fun add(card: Card): Cards {
        return Cards(cards + card)
    }

    fun merge(other: Cards): Cards {
        return Cards(cards + other.cards)  // 두 개의 Items 객체의 아이템 합치기
    }

    fun toList(): List<Card> = cards.toList()

    fun calculateScore(): Int {
        val totalScore = cards.sumOf { it.score() }

        return if (totalScore > BLACKJACK) {
            cards.sumOf { it.score(false) }
        } else {
            totalScore
        }
    }
}
