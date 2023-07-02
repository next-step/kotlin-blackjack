package card

import card.CardTest.Companion.HEART_ACE
import card.CardTest.Companion.SPADE_ACE
import card.CardTest.Companion.SPADE_JACK
import card.CardTest.Companion.SPADE_KING
import card.CardTest.Companion.SPADE_QUEEN
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class HandTest : FunSpec({
    context("addCard") {
        forAll(
            row(SPADE_ACE),
            row(SPADE_JACK),
            row(HEART_ACE),
        ) { input ->
            test("카드 한장을 추가합니다.") {
                val actual = Hand(Cards(setOf(SPADE_KING, SPADE_QUEEN))).addCard(input).cards.values.size
                actual shouldBe 3
            }
        }

        forAll(
            row(SPADE_ACE),
            row(SPADE_JACK),
            row(HEART_ACE),
        ) { input ->
            test("진행중인 Hand는 두장이상의 카드를 들고 있어야합니다.") {
                val hand = Hand(Cards(setOf(SPADE_KING)))
                val exception = shouldThrowExactly<IllegalStateException> { hand.addCard(input) }
                exception.message shouldBe "진행 중인 Hand는 두장 이상의 카드를 들고 있어야합니다."
            }
        }

        forAll(
            row(SPADE_ACE),
            row(SPADE_JACK),
            row(HEART_ACE),
        ) { input ->
            test("같은 카드를 추가할 수 없습니다.") {
                val hand = Hand(Cards(setOf(input, SPADE_KING)))
                val exception = shouldThrowExactly<IllegalArgumentException> { hand.addCard(input) }
                exception.message shouldBe "중복되는 카드는 추가할 수 없습니다."
            }
        }
    }

    context("addTwoCards") {
        forAll(
            row(TwoCards(SPADE_KING, SPADE_QUEEN)),
            row(TwoCards(HEART_ACE, SPADE_QUEEN)),
        ) { input ->
            val hand = Hand(Cards()).addTwoCards(input)
            hand.cards.values.size shouldBe 2
        }

        forAll(
            row(TwoCards(SPADE_KING, SPADE_QUEEN)),
            row(TwoCards(HEART_ACE, SPADE_QUEEN)),
        ) { input ->
            val hand = Hand(Cards(linkedSetOf(SPADE_JACK)))
            val exception = shouldThrowExactly<IllegalStateException> { hand.addTwoCards(input) }
            exception.message shouldBe "두장을 드로우 하려면 첫시작이어야 합니다."
        }
    }
})
