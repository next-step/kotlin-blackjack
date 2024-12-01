package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class CardNumberTest : FunSpec({
    context("CardNumber의 values 속성 테스트") {
        test("ACE는 1과 11을 포함해야 한다") {
            CardNumber.ACE.scores shouldBe listOf(1, 11)
        }

        test("숫자 카드(2~10)는 자신만의 값을 가져야 한다") {
            CardNumber.TWO.scores shouldBe listOf(2)
            CardNumber.THREE.scores shouldBe listOf(3)
            CardNumber.FOUR.scores shouldBe listOf(4)
            CardNumber.FIVE.scores shouldBe listOf(5)
            CardNumber.SIX.scores shouldBe listOf(6)
            CardNumber.SEVEN.scores shouldBe listOf(7)
            CardNumber.EIGHT.scores shouldBe listOf(8)
            CardNumber.NINE.scores shouldBe listOf(9)
            CardNumber.TEN.scores shouldBe listOf(10)
        }

        test("그림 카드(KING, QUEEN, JACK)는 10을 포함해야 한다") {
            CardNumber.KING.scores shouldBe listOf(10)
            CardNumber.QUEEN.scores shouldBe listOf(10)
            CardNumber.JACK.scores shouldBe listOf(10)
        }
    }

    context("CardNumber 열거형의 모든 값 테스트") {
        test("모든 CardNumber는 정확히 하나의 values 리스트를 가져야 한다") {
            CardNumber.entries.forEach { cardNumber ->
                cardNumber.scores shouldNotBe null
                cardNumber.scores.isNotEmpty() shouldBe true
            }
        }
    }

    context("특정 카드의 값 확인 테스트") {
        test("특정 카드 값을 계산 시 정확히 동작해야 한다") {
            CardNumber.ACE.scores.contains(1) shouldBe true
            CardNumber.ACE.scores.contains(11) shouldBe true
            CardNumber.KING.scores.contains(10) shouldBe true
            CardNumber.TEN.scores.contains(10) shouldBe true
        }
    }
})
