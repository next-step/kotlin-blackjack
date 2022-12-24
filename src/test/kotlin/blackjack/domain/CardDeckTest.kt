package blackjack.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.FunSpec

class CardDeckTest : FunSpec({
    context("객체 생성") {
        test("카드 덱을 생성한다.") {
            shouldNotThrowAny {
                CardDeck()
            }
        }
    }
})
