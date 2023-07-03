package card

import card.CardTest.Companion.CLOVER_ACE
import card.CardTest.Companion.SPADE_ACE
import card.CardTest.Companion.SPADE_JACK
import card.CardTest.Companion.SPADE_KING
import card.CardTest.Companion.SPADE_QUEEN
import card.CardTest.Companion.SPADE_TWO
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class CardsTest : FunSpec({
    context("init") {
        test("카드 리스트를 생성한다.") {
            val actual = Cards(linkedSetOf(SPADE_ACE, SPADE_TWO))
            actual.values shouldHaveSize 2
        }
    }

    context("isBust") {
        forAll(
            row(linkedSetOf(SPADE_ACE, SPADE_TWO), false),
            row(linkedSetOf(SPADE_ACE, SPADE_KING, SPADE_JACK), false),
            row(linkedSetOf(SPADE_ACE, SPADE_KING, SPADE_JACK, SPADE_QUEEN), true),
            row(linkedSetOf(SPADE_TWO, SPADE_KING, SPADE_JACK), true),
        ) { input, expected ->
            test("${input}은 버스트가 ${expected}이다") {
                val cards = Cards(input)
                val actual = cards.isBust()
                actual shouldBe expected
            }
        }
    }

    context("score") {
        forAll(
            row(linkedSetOf(SPADE_ACE, SPADE_TWO), 13),
            row(linkedSetOf(SPADE_ACE, SPADE_KING, SPADE_JACK), 21),
            row(linkedSetOf(SPADE_ACE, CLOVER_ACE), 12),
            row(linkedSetOf(CLOVER_ACE, SPADE_KING, SPADE_JACK), 21),
        ) { input, expected ->
            test("${input}의 최고 점수는 $expected") {
                val actual = Cards(input).score()
                actual shouldBe expected
            }
        }
    }
})
