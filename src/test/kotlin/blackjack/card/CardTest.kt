package blackjack.card

import blackjack.entity.CardNumber
import blackjack.entity.CardShape
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class CardTest : StringSpec({
    "카드 숫자 1~10의 숫자는 에러를 발생시키지 않는다." {
        val inputList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        shouldNotThrow<IllegalStateException> {
            inputList.forEach { CardNumber.findCardNumberFromNumber(it) }
        }
    }

    "카드 숫자 1(A) 보다 작은 값이 들어가면 에러를 발생시킨다." {
        shouldThrow<IllegalStateException> {
            CardNumber.findCardNumberFromNumber(0)
        }
    }

    "카드 숫자에 11 이상의 값을 입력시에 에러를 발생시킨다." {
        shouldThrow<IllegalStateException> {
            CardNumber.findCardNumberFromNumber(11)
        }
    }

    "카드 모양에 하트, 스페이드, 클로버, 다이아 외의 값을 입력시 에러를 발생시킨다." {
        shouldThrow<IllegalStateException> {
            CardShape.findCardShapeFromName("별")
        }
    }

    "카드 모양에 하트, 스페이드, 클로버, 다이아는 에러를 발생 안 시킨다." {
        val inputList = listOf("다이아", "하트", "클로버", "스페이드")
        shouldNotThrow<IllegalStateException> {
            inputList.forEach { CardShape.findCardShapeFromName(it) }
        }
    }
})
