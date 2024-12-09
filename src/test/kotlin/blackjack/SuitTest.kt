package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize

class SuitTest : StringSpec({
    "Suit 는 자신의 문양을 가진 모든 카드를 만들어 반환할 수 있다" {
        val suit = Suit.SPADES
        val results: List<Card> = suit.generateAllCards2()
        val expected = Denomination.map { Card(suit, it) }

        results shouldHaveSize 13
        results shouldContainExactlyInAnyOrder expected
    }
})
