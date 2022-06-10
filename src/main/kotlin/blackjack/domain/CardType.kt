package blackjack.domain

import kotlin.random.Random

/**
 * 카드 타입을 설정하는 클래스
 * Created by Jaesungchi on 2022.06.07..
 */
enum class CardType(val title: String) {
    SPADE("스페이드"),
    CLOVER("클로버"),
    HEART("하트"),
    DIAMOND("다이아");

    companion object {
        // 나중에 테스트 가능하게 변경
        fun pick(): CardType {
            val random = Random.nextInt(CardType.values().size)
            return CardType.values()[random]
        }
    }
}
