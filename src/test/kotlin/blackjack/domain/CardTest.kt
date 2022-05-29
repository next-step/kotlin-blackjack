package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class CardTest : StringSpec({

    "문양과 숫자를 받아 인스턴스를 생성한다" {
        val card = Card(Suite.SPADES, Denomination.ACE)

        card.suite shouldBe Suite.SPADES
        card.denomination shouldBe Denomination.ACE
    }
})
