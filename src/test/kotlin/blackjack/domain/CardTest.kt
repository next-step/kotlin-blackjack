package blackjack.domain

import blackjack.domain.Rank.ACE
import blackjack.domain.Rank.THREE
import blackjack.domain.Suit.SPADE
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "카드는 에이스로 만들어진다면 에이스 카드이다" {
        val card = Card(ACE, SPADE)

        card.isAce() shouldBe true
    }

    "카드는 스페이드로 만들어진다면 에이스 카드가 아니다" {
        val card = Card(THREE, SPADE)

        card.isAce() shouldBe false
    }
})
