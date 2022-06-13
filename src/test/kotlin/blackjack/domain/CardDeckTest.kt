package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CardDeckTest : FreeSpec({
    lateinit var takeCards: Cards
    "처음 생성된 덱은 총 52개의 카드를 가진다" {
        CardDeck.all().size shouldBe 52
    }

    "랜덤하게 카드를 한개 가져온다" {
        takeCards.add(CardDeck.hit())
        takeCards.size() shouldBe 3
    }
})
