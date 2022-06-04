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
                    Card(CardPattern.CLOVER, Ace()) shouldBe Card(CardPattern.CLOVER, Ace())
                    Card(CardPattern.HEART, NumberCard(5)) shouldBe Card(CardPattern.HEART, NumberCard(5))
                }
            }
        }

        context("다른 문양과 다른 유형의 카드가 주어지면") {
            it("두 카드는 동일하지 않은 카드이다") {
                assertSoftly {
                    Card(CardPattern.CLOVER, Ace()) shouldNotBe Card(CardPattern.HEART, Ace())
                    Card(CardPattern.HEART, NumberCard(5)) shouldNotBe Card(CardPattern.SPADE, NumberCard(5))
                }
            }
        }

        context("같은 문양과 다른 유형의 카드가 주어지면") {
            it("두 카드는 동일하지 않은 카드이다") {
                assertSoftly {
                    Card(CardPattern.CLOVER, Ace()) shouldNotBe Card(CardPattern.CLOVER, Queen())
                    Card(CardPattern.HEART, NumberCard(5)) shouldNotBe Card(CardPattern.CLOVER, NumberCard(10))
                }
            }
        }
    }
})
