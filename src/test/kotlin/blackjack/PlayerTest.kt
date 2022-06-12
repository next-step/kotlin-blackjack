package blackjack

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal class PlayerTest : FunSpec({
    test("손패에 가지고 있는 카드의 개수를 알 수 있다.") {
        // given
        val values = ArrayDeque(listOf(Card(CardNumber.ACE, CardSuit.SPADE)))
        val sut = Player(name = "gomding", Cards(values))

        // when
        val result = sut.handSize()

        // then
        result shouldBe 1
    }

    test("플레이어는 손패에 카드를 추가할 수 있다.") {
        // given
        val sut = Player(name = "gomding")

        // when
        sut.addCardToHand(Card(CardNumber.ACE, CardSuit.SPADE))

        // then
        sut.handSize() shouldBe 1
    }
})
