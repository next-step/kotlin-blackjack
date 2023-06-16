package blackjack.domain.deck

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DenominationTest : StringSpec({

    "Denomination 노출 이름과 스코어들을 보관하며, 최소 스코어를 가져올 수 있다." {
        val ace = Denomination.ACE

        ace.exposeName shouldBe "A"
        ace.getMinimumScore() shouldBe 1
        ace.scores shouldBe arrayOf(1, 11)
    }
})
