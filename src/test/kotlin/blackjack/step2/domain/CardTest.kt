package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CardTest : FunSpec({
    test("Card 생성 테스트") {
        // given
        val givenCardNumber = CardNumber.TWO
        val givenCardType = CardType.SPADE

        // when
        val card = Card(number = givenCardNumber, type = givenCardType)

        // then
        card.number shouldBe givenCardNumber
        card.type shouldBe givenCardType
    }
})
