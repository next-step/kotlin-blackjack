package blackjack.domain.gamestate.finished

import blackjack.domain.card.CardTest.Companion.SPADE_ACE
import blackjack.domain.card.CardTest.Companion.SPADE_KING
import blackjack.domain.card.CardTest.Companion.SPADE_TWO
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

        test("생성 시 카드 점수가 21이 아니면 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { Blackjack(Cards.of(SPADE_KING, SPADE_TWO)) }
            exception.message shouldBe "21인 카드만 블랙잭이 될 수 있다."
        }
    }

    context("isBust") {
        test("blackjack은 bust가 아니다.") {
            val actual = Blackjack(Cards.of(SPADE_KING, SPADE_ACE)).isBust()
            actual shouldBe false
        }
    }
})
