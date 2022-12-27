package blackjack.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.types.shouldBeInstanceOf
import java.lang.IllegalStateException

class CardDeckTest : FunSpec({
    context("객체 생성") {
        test("카드 덱을 생성한다.") {
            shouldNotThrowAny {
                CardDeck()
            }
        }
    }

    context("pick()") {
        test("카드 1장을 뽑는다.") {
            val cardDeck = CardDeck()

            val actual = cardDeck.pick()

            actual.shouldBeInstanceOf<Card>()
        }
        test("뽑을 카드가 없을 경우, 예외가 발생한다.") {
            shouldThrow<IllegalStateException> {
                CardDeck(emptySet()).pick()
            }
        }
    }
})
