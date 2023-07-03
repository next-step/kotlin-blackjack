package blackjack.domain.gamestate.finished

import blackjack.domain.card.Cards
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BlackjackTest : FunSpec({

    context("init") {
        test("생성 시 카드가 initialhand면 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { Blackjack(Cards()) }
            exception.message shouldBe "2장 미만의 카드로 생성될 수 없다."
        }
    }

})
