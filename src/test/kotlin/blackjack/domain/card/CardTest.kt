package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "Ace 카드는 총합이 11 을 넘으면 1값을 반환 한다." {
        val card = Card(Suit.HEART, Number.ACE)
        val point = card.getPoint(12)

        point shouldBe 1
    }

    "Ace 카드는 총합이 11 을 넘지 않으면 11값을 반환 한다." {
        val card = Card(Suit.HEART, Number.ACE)
        val point = card.getPoint(1)

        point shouldBe 11
    }
})
