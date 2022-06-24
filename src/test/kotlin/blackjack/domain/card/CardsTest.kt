package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class CardsTest : StringSpec({
    "카드들의 합을 구하는 기능" {
        listOf(
            row(
                mutableListOf(
                    Card(Suit.DIAMOND, Face.TWO),
                    Card(Suit.DIAMOND, Face.THREE),
                ),
                5
            ),
            row(
                mutableListOf(
                    Card(Suit.DIAMOND, Face.TWO),
                    Card(Suit.DIAMOND, Face.TEN),
                ),
                12
            ),
            row(
                mutableListOf(
                    Card(Suit.DIAMOND, Face.ACE),
                    Card(Suit.DIAMOND, Face.TEN),
                ),
                21
            ),
        ).forEach { (cardGroup, expected) ->
            // given
            val cards = Cards(cardGroup)

            // when
            val actual = cards.calculateScore()

            // then
            actual shouldBe expected
        }
    }
})
