package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class CardTypeTest : FunSpec({
    context("CardType enum 클래스 테스트") {
        test("모든 CardType의 value 값이 올바른지 확인한다.") {
            CardType.SPADE.value shouldBe "스페이드"
            CardType.DIAMOND.value shouldBe "다이아몬드"
            CardType.HEART.value shouldBe "하트"
            CardType.CLOVER.value shouldBe "클로버"
        }

        test("CardType.entries로 모든 CardType을 순회할 수 있다.") {
            val cardTypes = CardType.entries
            cardTypes.size shouldBe 4
            cardTypes shouldBe
                listOf(
                    CardType.SPADE,
                    CardType.DIAMOND,
                    CardType.HEART,
                    CardType.CLOVER,
                )
        }

        test("CardType의 toString이 기본 Enum의 이름과 일치하는지 확인한다.") {
            CardType.SPADE.toString() shouldBe "SPADE"
            CardType.DIAMOND.toString() shouldBe "DIAMOND"
            CardType.HEART.toString() shouldBe "HEART"
            CardType.CLOVER.toString() shouldBe "CLOVER"
        }

        test("CardType.random()을 호출하면 랜덤한 CardType을 반환한다") {
            // when
            val randomCardType = CardType.random()

            // then
            CardType.entries shouldContain randomCardType
        }
    }
})
