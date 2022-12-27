package blackjack.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.FunSpec

class CardTest : FunSpec({
    context("객체 생성") {
        test("카드 값과 패턴을 가진 카드를 생성한다.") {
            shouldNotThrowAny {
                Card(CardPattern.DIAMOND, CardValue.ACE)
            }
        }
    }
})
