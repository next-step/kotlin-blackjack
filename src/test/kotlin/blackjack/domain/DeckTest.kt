package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({
    "덱은 52장의 카드를 가진다" {
        val actual = Deck()

        actual.quantity shouldBe 52
    }

    "카드를 한 장 뽑으면 덱의 크기가 1 줄어든다" {
        val actual = Deck()
        val beforeSize = actual.quantity

        actual.draw()

        actual.quantity shouldBe beforeSize - 1
    }

    "뽑을 카드가 없으면 에러가 발생한다" {
        val actual = Deck()
        repeat(52) {
            actual.draw()
        }

        shouldThrow<IllegalStateException> {
            actual.draw()
        }
    }
})
