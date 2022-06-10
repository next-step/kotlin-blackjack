package blackjack.domain

import kotlin.random.Random

/**
 * 카드 숫자를 저장하는 클래스
 * Created by Jaesungchi on 2022.06.07..
 */
enum class CardNumber(val title: String, val score: Int) {
    ACE("A", 1),
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
        // 나중에 테스트 가능하게 변경
        fun pick(): CardNumber {
            val random = Random.nextInt(CardNumber.values().size)
            return CardNumber.values()[random]
        }
    }
}
