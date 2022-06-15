package blackjack.view

import blackjack.domain.dto.ProfitResult
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class ProfitViewTest : StringSpec({

    "최종 수익에 대한 정보를 출력한다" {
        val io = StubIO()
        val profitResult = listOf(
            ProfitResult(name = "dealer", profit = -1500.0),
            ProfitResult(name = "player", profit = 100.5),
        )

        ProfitView(io).printProfitResult(profitResult)

        io.printed shouldBe listOf(
            "## 최종 수익",
            "dealer: -1500",
            "player: 100.5",
        )
    }
})
