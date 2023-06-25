package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class TurnTest : FunSpec({

    context("isDealingTurn") {
        forAll(
            row(-1, true),
            row(0, false),
        ) { input, expected ->
            test("${input}이 딜링턴인지 확인한다.") {
                val actual = Turn(input).isDealingTurn()
                actual shouldBe expected
            }
        }

    }

    context("nextTurn") {
        test("다음 턴을 반환한다.") {
            val turn = Turn(0)
            val actual = turn.nextTurn()
            actual shouldBe Turn(1)
        }
    }

    context("isSameTurn") {
        test("동일한 턴의 value인지 확인한다.") {
            val turn = Turn(1)
            val actual = turn.isSameTurn(1)
            actual shouldBe true
        }
    }

    context("isHigherTurn") {
        test("더 높은 턴인지 확인한다.") {
            val turn = Turn(1)
            val actual = turn.isHigherTurn(2)
            actual shouldBe true
        }
    }
})
