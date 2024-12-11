package blackjack.domain.card

import blackjack.fixtures.createCard
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class HandTest : BehaviorSpec({
    Given("카드목록의 점수 합이 21점을 초과하는 경우") {
        table(
            headers("ranks", "expected"),
            row(listOf("A", "2", "10"), 13),
            row(listOf("A", "2", "2", "K"), 15),
            row(listOf("A", "A"), 12),
            row(listOf("A", "A", "A"), 13),
            row(listOf("A", "3", "9"), 13),
        ).forAll { ranks, expected ->
            val hand = Hand(ranks.map { createCard(it) }.toMutableList())

            When("점수 계산하면") {
                Then("에이스는 1점으로 보정된다") {
                    hand.score shouldBe expected
                }
            }
        }
    }
})
