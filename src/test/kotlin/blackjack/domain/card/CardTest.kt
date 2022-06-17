package blackjack.domain.card

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class CardTest : DescribeSpec({

    describe("equals") {
        context("같은 카드 문양과 같은 유형이 주어지면") {
            it("두 카드는 동일한 카드이다") {
                assertSoftly {
                    card { "클로버" to "A" } shouldBe Card(Suit.CLOVER, Ace())
                    card { "하트" to 5 } shouldBe Card(Suit.HEART, NumberCard(5))
                }
            }
        }

        context("다른 문양과 다른 유형의 카드가 주어지면") {
            it("두 카드는 동일하지 않은 카드이다") {
                assertSoftly {
                    card { "클로버" to "A" } shouldNotBe Card(Suit.HEART, Ace())
                    card { "하트" to 5 } shouldNotBe Card(Suit.SPADE, NumberCard(5))
                }
            }
        }

        context("같은 문양과 다른 유형의 카드가 주어지면") {
            it("두 카드는 동일하지 않은 카드이다") {
                assertSoftly {
                    card { "클로버" to "A" } shouldNotBe Card(Suit.CLOVER, Queen())
                    card { "하트" to 5 } shouldNotBe Card(Suit.CLOVER, NumberCard(10))
                }
            }
        }
    }
})
