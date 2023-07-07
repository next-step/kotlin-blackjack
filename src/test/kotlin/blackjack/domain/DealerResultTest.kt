package blackjack.domain

import blackjack.domain.result.DealerResult
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerResultTest : StringSpec(
    {
        "딜러의 승,패의 수를 설정할 수 있다." {
            val dealerResult = DealerResult(5, 1)

            dealerResult.winCount shouldBe 5
            dealerResult.loseCount shouldBe 1
        }
    })
