package model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PokerNumberTest : StringSpec({

    "포커숫자 조회를 하면  14개를 반환한다" {
        // when
        val pokerNumbers = PokerNumber.values()
        // then
        pokerNumbers.size shouldBe 13
    }
})
