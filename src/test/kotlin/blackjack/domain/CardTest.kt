package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

internal class CardTest : StringSpec({

    "문양과 숫자를 받아 인스턴스를 생성한다" {
        val card = Card(Suite.SPADES, Denomination.ACE)

        card.suite shouldBe Suite.SPADES
        card.denomination shouldBe Denomination.ACE
    }

    "Denomination 가 Ace 이면 isAce 결과는 true 이다" {
        val card = Card(Suite.SPADES, Denomination.ACE)

        card.isAce.shouldBeTrue()
    }

    "Denomination 가 Ace 가 아니면 isAce 결과는 false 이다" {
        val card = Card(Suite.SPADES, Denomination.TWO)

        card.isAce.shouldBeFalse()
    }
})
