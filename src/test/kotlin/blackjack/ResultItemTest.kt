package blackjack

import blackjack.domain.CompareResultItem
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

data class ResultItemPlusTestData(val a: CompareResultItem, val b: CompareResultItem)

class ResultItemTest : FunSpec({
    context("ResultItem 덧셈") {
        withData(
            listOf(
                ResultItemPlusTestData(
                    CompareResultItem(
                        1,
                        1,
                        2,
                    ),
                    CompareResultItem(
                        3,
                        4,
                        2,
                    )
                )
            )
        ) { (a, b) ->
            val actual = a + b

            actual shouldBe CompareResultItem(
                a.win + b.win,
                a.draw + b.draw,
                a.lose + b.lose
            )
        }
    }
})
