package blackjack.domain

import blackjack.domain.Suit.SPADE
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "카드는 에이스로 만들어진다면 에이스 카드이다" {
        val card = Card(Rank("A"), SPADE)

        card.isAce() shouldBe true
    }

    "카드는 스페이드로 만들어진다면 에이스 카드가 아니다" {
        val card = Card(Rank("3"), SPADE)

        card.isAce() shouldBe false
    }
})
