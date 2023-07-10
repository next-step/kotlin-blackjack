package blackjack.domain

import blackjack.domain.result.DealerResult
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerResultTest : StringSpec(
    {
        "딜러의 최종 수익을 설정할 수 있다." {
            val dealerResult = DealerResult(50000)

            dealerResult.finalRevenue shouldBe 50000
        }
    })
