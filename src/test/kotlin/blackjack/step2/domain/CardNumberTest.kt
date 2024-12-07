package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class CardNumberTest : FunSpec({
    context("CardNumber 기본 점수 확인") {
        test("ACE는 기본 점수 1을 가져야 한다") {
            CardNumber.ACE.score shouldBe 1
        }

        test("숫자 카드(2~10)는 자신만의 점수를 가져야 한다") {
            CardNumber.TWO.score shouldBe 2
            CardNumber.THREE.score shouldBe 3
            CardNumber.FOUR.score shouldBe 4
            CardNumber.FIVE.score shouldBe 5
            CardNumber.SIX.score shouldBe 6
            CardNumber.SEVEN.score shouldBe 7
            CardNumber.EIGHT.score shouldBe 8
            CardNumber.NINE.score shouldBe 9
            CardNumber.TEN.score shouldBe 10
        }

        test("그림 카드(KING, QUEEN, JACK)는 점수 10을 가져야 한다") {
            CardNumber.KING.score shouldBe 10
            CardNumber.QUEEN.score shouldBe 10
            CardNumber.JACK.score shouldBe 10
        }

        test("CardNumber.random()을 호출하면 랜덤한 CardNumber를 반환한다") {
            // when
            val randomCardNumber = CardNumber.random()

            // then
            CardNumber.entries shouldContain randomCardNumber
        }
    }
})
