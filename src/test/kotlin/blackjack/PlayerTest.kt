package blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class PlayerTest : StringSpec({
    "플레이어는 이름이 빈 문자열일 수 없다" {
        val name = " "
        val initialCards =
            listOf(
                Card(CardNumber.QUEEN, Suit.SPADES),
                Card(CardNumber.KING, Suit.SPADES),
            )

        shouldThrow<IllegalArgumentException> {
            Player(name = name, initialCards = initialCards)
        }
    }
})
