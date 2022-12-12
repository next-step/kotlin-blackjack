package blackjack

import blackjack.domain.Denomination
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class DenominationTest : BehaviorSpec({
    given("블랙잭 게임에서") {
        `when`("플레잉 카드를 사용할 때") {
            then("13개의 끗수가 존재한다.") {
                Denomination.values().size shouldBe 13
            }
        }
    }
})
