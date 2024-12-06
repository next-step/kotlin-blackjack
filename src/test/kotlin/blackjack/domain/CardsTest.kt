package blackjack.domain

import blackjack.fixtures.createCard
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class CardsTest : StringSpec({
    "카드목록의 점수합이 21을 초과할 경우 에이스는 1점으로 보정된다" {
        table(
            headers("ranks", "expected"),
            row(listOf("A", "2", "10"), 13),
            row(listOf("A", "2", "2", "K"), 15),
            row(listOf("A", "A"), 12),
            row(listOf("A", "A", "A"), 13),
            row(listOf("A", "3", "9"), 13),
        ).forAll { ranks, expected ->
            val cards = Cards(ranks.map { createCard(it) })

            cards.score shouldBe expected
        }
    }
})
