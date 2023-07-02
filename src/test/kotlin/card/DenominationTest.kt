package card

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe

class DenominationTest : FunSpec({
    context("init") {
        test("ACE는 두가지 값을 가질 수 있습니다.") {
            val actual = Denomination.ACE.score
            actual shouldContainAll listOf(1, 11)
        }
        forAll(
            row(Denomination.JACK),
            row(Denomination.QUEEN),
            row(Denomination.KING),
        ) { input ->
            test("${input}은 10으로 계산됩니다.") {
                input.score shouldBe listOf(10)
            }
        }

        forAll(
            row(Denomination.TWO, listOf(2)),
            row(Denomination.THREE, listOf(3)),
            row(Denomination.FOUR, listOf(4)),
            row(Denomination.FIVE, listOf(5)),
            row(Denomination.SIX, listOf(6)),
            row(Denomination.SEVEN, listOf(7)),
            row(Denomination.EIGHT, listOf(8)),
            row(Denomination.NINE, listOf(9)),
        ) { input, expected ->
            val actual = input.score
            actual shouldBe expected
        }
    }
})
