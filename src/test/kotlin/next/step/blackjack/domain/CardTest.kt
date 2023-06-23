package next.step.blackjack.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CardTest : DescribeSpec({
    describe("card method") {
        context("desc를 호출하면") {
            it("card face와 card symbol을 붙여서 제공한다.") {
                Card.of(CardFace.ACE, CardSymbol.CLUB).desc() shouldBe "A클로버"
            }
        }
    }

})
