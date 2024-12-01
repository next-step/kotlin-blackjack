package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CardTest : FunSpec({
    test("Card를 생성할때 이름을 입력하면 이름이 저장된다.") {
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
