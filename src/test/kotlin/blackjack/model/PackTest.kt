package blackjack.model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize

class PackTest : StringSpec({
    "모든 카드를 중복없이 한장씩만 뽑을수 있어야 한다" {
        val actual = (1..52).map {
            Pack.anyCard()
        }.toSet()
        actual shouldHaveSize 52
    }
})
