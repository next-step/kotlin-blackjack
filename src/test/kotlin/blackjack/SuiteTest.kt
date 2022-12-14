package blackjack

import blackjack.domain.Suite
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class SuiteTest : StringSpec({
    "카드는 4개의 슈트가 존재한다." {
        Suite.values().size shouldBe 4
    }
})
