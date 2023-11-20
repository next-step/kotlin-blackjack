package blackjack_dealer

import blackjack_dealer.domain.Participant
import blackjack_dealer.entity.CardDeque
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackTest : StringSpec({
    val cardDeque = CardDeque.create()
    "블랙잭을 수행하여 한장 더 받기를 선택시에 카드의 숫자가 한장 증가한다" {
        val participant = Participant.newInstance("pita")
        val expected = 3
        // 블랙잭 수행

        // 한장만 더 받기

        participant.getCurrentCards().count() shouldBe expected
    }

    "블랙잭을 수행하여 한장 더 안받기 선택시에 카드의 숫자가 한장 증가한다" {
        val participant = Participant.newInstance("pita")
        val expected = 2
        // 블랙잭 수행

        // 한장만 더 안 받기

        participant.getCurrentCards().count() shouldBe expected
    }
})
