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

    context("calculateScore 테스트") {
        test("ACE를 11로 사용할 수 있는 경우") {
            // given
            val currentScore = 10

            // when
            val aceScore = CardNumber.ACE.calculateScore(currentScore = currentScore)

            // then
            aceScore shouldBe 11
        }

        test("ACE를 1로 사용할 수밖에 없는 경우") {
            // given
            val currentScore = 12

            // when
            val aceScore = CardNumber.ACE.calculateScore(currentScore = currentScore)

            // then
            aceScore shouldBe 1
        }

        test("숫자 카드의 점수는 그대로 반환된다") {
            CardNumber.TWO.calculateScore(currentScore = 10) shouldBe 2
            CardNumber.THREE.calculateScore(currentScore = 15) shouldBe 3
        }

        test("그림 카드의 점수는 그대로 반환된다") {
            CardNumber.KING.calculateScore(currentScore = 15) shouldBe 10
            CardNumber.QUEEN.calculateScore(currentScore = 0) shouldBe 10
            CardNumber.JACK.calculateScore(currentScore = 20) shouldBe 10
        }
    }

    context("CardNumber.random() 테스트") {
        test("CardNumber.random()은 null이 아닌 유효한 값을 반환해야 한다") {
            repeat(13) {
                CardNumber.entries shouldContain CardNumber.random()
            }
        }
    }
})
