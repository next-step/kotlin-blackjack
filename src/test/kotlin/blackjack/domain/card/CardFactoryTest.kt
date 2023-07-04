package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class CardFactoryTest : StringSpec({

    "카드를 초기화 시킨다." {
        val cards = CardFactory.defaultCards

        cards shouldHaveSize 48

        val expectedDenominations = Denomination.values().toList()
        val expectedSuits = Suit.values().toList()

        for (suit in expectedSuits) {
            for (denomination in expectedDenominations) {
                val card = Card(denomination, suit)
                cards shouldContainAll listOf(card)
            }
        }
    }
})
