package next.step.blackjack.domain.card

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CardFaceTest : DescribeSpec({

    describe("CardFace method") {
        context("ACE.maxPoint()") {
            it("최대 포인트를 제공") {
                CardFace.ACE.maxPoint() shouldBe 11
            }
        }
        context("ACE.minPoint()") {
            it("최소 포인트를 제공") {
                CardFace.ACE.minPoint() shouldBe 1
            }
        }
        context("ACE가 아닌 CardFace는 최대 최소값 모두 point와 같음") {
            withData(
                CardFace.values().filterNot { it == CardFace.ACE }
            ) { cardFace ->
                assertSoftly {
                    cardFace.maxPoint() shouldBe cardFace.point
                    cardFace.minPoint() shouldBe cardFace.point
                }
            }
        }
    }
})
