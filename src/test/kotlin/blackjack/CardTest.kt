package blackjack

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal class CardTest : FunSpec({
    test("카드는 하나의 숫자와 하나의 무늬를 가진다.") {
        // given
        val sut = Card(CardNumber.ACE, CardSuit.SPADE)

        // then
        sut shouldBe Card(CardNumber.ACE, CardSuit.SPADE)
    }
})
