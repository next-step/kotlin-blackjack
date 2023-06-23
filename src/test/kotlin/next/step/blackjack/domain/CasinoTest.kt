package next.step.blackjack.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CasinoTest : DescribeSpec({

    describe("Casino init") {
        context("카지노가 생성되면") {
            it("6덱 만큼의 카드가 생긴다.") {
                Casino.cards.size shouldBe 6 * CardFace.values().size * CardSymbol.values().size
            }
        }
    }

})
