package blackjack.domain

import domain.Card
import domain.CardNumber
import domain.CardShape
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class CardTest : FreeSpec({

    "카드는 생성하면" - {
        val card = Card(shape = CardShape.HEART, number = CardNumber.ACE)

        "카드 모양을 가지고 있다" {
            card.shape.shouldNotBeNull()
        }

        "카드 숫자를 가지고 있다" {
            card.number.shouldNotBeNull()
        }

        "Ace 카드는 1 또는 11로 계산할 수 있어야 한다" {
            CardNumber.ACE.primaryScore shouldBe 1
            CardNumber.ACE.secondaryScore shouldBe 11
        }
    }
})
