package blackjack

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal class CardDeckTest : FunSpec({
    test("표준 카드 덱은 13개의 숫자와 4개의 무늬로 이뤄진, 42장의 카드를 가진다.") {
        // when
        val sut = CardDeck.createDeck()

        // then
        sut.size() shouldBe 52
    }

    test("카드뭉치에서 가장 위에있는 한장의 카드를 뽑을 수 있다.") {
        // given
        val sut = CardDeck(ArrayDeque(listOf(Card(CardNumber.ACE, CardSuit.SPADE), Card(CardNumber.TWO, CardSuit.SPADE))))

        // when
        val result = sut.draw()

        // then
        result shouldBe Card(CardNumber.TWO, CardSuit.SPADE)
    }
})
