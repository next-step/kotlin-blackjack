package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DeckTest : FunSpec({
    test("게임에 필요한 카드를 가지는 덱을 생성한다.") {
        // given
        val deck = Deck.createOf()

        // when
        val actual = deck.drawFirstTurn()

        // then
        actual.size shouldBe 2
    }
})
