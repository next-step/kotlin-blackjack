package blackjack.domain

import blackjack.constant.ErrorMessages
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
        fun pick(title: String? = null): CardType {
            title?.let {
                return CardType.values().find { it.title == title }
                    ?: throw IllegalArgumentException(ErrorMessages.NOT_CARD_TYPE)
            }
            val random = Random.nextInt(CardType.values().size)
            return CardType.values()[random]
        }
    }
}
