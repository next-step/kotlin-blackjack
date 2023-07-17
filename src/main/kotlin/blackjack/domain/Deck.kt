package blackjack.domain

/**
 * ### 플레이어가 소지한 카드를 표현하는 객체 입니다.
 *
 * 카드를 추가할 수 있고, 소지한 카드들에 대한 점수를 확인할 수 있습니다.
 */
class Deck(val cards: List<Card> = listOf()) {

    val size: Int
        get() = cards.size

    fun score(): Score {
        return BlackjackCardPointCalculator.calculate(cards)
    }

    fun isBurst(): Boolean {
        return score().isBurst
    }

    fun isBlackjack(): Boolean {
        return size == 2 && score().isBlackjack
    }

    operator fun plus(card: Card): Deck {
        return Deck(cards + card)
    }
}
