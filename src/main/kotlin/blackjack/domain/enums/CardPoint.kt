package blackjack.domain.enums

import blackjack.domain.Card
import kotlin.random.Random

enum class CardPoint(val cardName: String, val point: Int) {
    ONE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    companion object {
        private val cardPoints = values().toList()
        private val size = cardPoints.size

        fun randomPoint(): CardPoint {
            return cardPoints[Random.nextInt(size)]
        }

        fun point(card: Card): Int {
            return values().find { it == card.point }?.point ?: throw IllegalArgumentException("비 정상적인 카드입니다.")
        }
    }
}
