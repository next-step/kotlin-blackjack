package blackjack.gamestate

import blackjack.CardTest.Companion.SPADE_ACE
import blackjack.CardTest.Companion.SPADE_TWO
import blackjack.Cards
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class HitTest : FunSpec({

    context("stay") {
        test("stay 상태로 변경한다.") {
            val actual = Hit(Cards.of(SPADE_ACE, SPADE_TWO)).stay()
            actual.shouldBeTypeOf<Stay>()
        }
    }
})
