package blackjack

import blackjack.domain.Card
import blackjack.domain.Denomination
import blackjack.domain.Suite
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class CardTest : StringSpec({
    "카드는 4개의 슈트중 1개의 슈트와 13개의 끗수중 1개의 끗수를 가진다." {
        val card = Card(Suite.DIAMOND, Denomination.ACE)
        card.suite shouldBe Suite.DIAMOND
        card.denomination shouldBe Denomination.ACE
    }
})
