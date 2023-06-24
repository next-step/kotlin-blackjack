package next.step.blackjack.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.CardFace
import next.step.blackjack.domain.card.CardSymbol

class CardTest : DescribeSpec({

    describe("card method") {
        context("desc를 호출하면") {
            it("card face와 card symbol을 붙여서 제공한다.") {
                Card.of(CardFace.ACE, CardSymbol.CLUB).desc() shouldBe "A클로버"
            }
        }
        context("minPoint 호출하면") {
            it("cardFace의 minPoint 제공") {
                Card.of(CardFace.ACE, CardSymbol.CLUB).minPoint() shouldBe CardFace.ACE.minPoint()
            }
        }
        context("maxPoint 호출하면") {
            it("cardFace의 maxPoint 제공") {
                Card.of(CardFace.ACE, CardSymbol.CLUB).maxPoint() shouldBe CardFace.ACE.maxPoint()
            }
        }
    }
})
