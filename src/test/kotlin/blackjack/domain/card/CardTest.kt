package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "카드의 Face가 ACE인지 확인한다." {
        listOf(
            row(Card(Suit.DIAMOND, Face.ACE), true),
            row(Card(Suit.DIAMOND, Face.TEN), false),
        ).forEach { (card, expected) ->
            // when
            val actual = card.isAce()

            // then
            actual shouldBe expected
        }
    }
})
