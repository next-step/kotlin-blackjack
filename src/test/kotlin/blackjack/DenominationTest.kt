package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class DenominationTest : StringSpec({
    "카드는 13개의 끗수가 존재한다." {
        Denomination.values().size shouldBe 13
    }
})
