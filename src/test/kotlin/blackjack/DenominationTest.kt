package blackjack

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe

class DenominationTest : FunSpec({

    context("init") {
        test("ace score는 1 또는 11을 가질 수 있다.") {
            val actual = Denomination.ACE.score

            actual shouldContainAll listOf(1, 11)
        }

        forAll(
            row(Denomination.KING),
            row(Denomination.QUEEN),
            row(Denomination.JACK),
        ) { input ->
            test("${input}은 score를 10을 가진다.") {
                val actual = input.score

                actual shouldBe listOf(10)
            }
        }
    }
})
