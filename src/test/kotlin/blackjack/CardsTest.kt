package blackjack

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal class CardsTest : FunSpec({
    test("표준 카드 덱은 13개의 숫자와 4개의 무늬로 이뤄진, 42장의 카드를 가진다.") {
        // when
        val sut = Cards.createDeck()

        // then
        sut.size() shouldBe 52
    }

    test("카드를 추가할 수 있다.") {
        // given
        val card = Card(CardNumber.ACE, CardSuit.SPADE)
        val sut = Cards()

        // when
        sut.add(card)

        // then
        sut.size() shouldBe 1
    }
})
