package blackjack.domain

import domain.Card
import domain.CardNumber
import domain.CardShape
import domain.Player
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class PlayerTest : FreeSpec({
    val player = Player()

    "플레이어는 카드를 들고 있을 수 있다" {
        player.hands.shouldNotBeNull()
    }

    "플레이어는 손의 든 카드의 숫자를 알 수 있다" {
        player.handsCardCount.shouldNotBeNull()
    }

    "플레이어는 카드를 받을 수 있다" {
        val beforeCount = player.handsCardCount
        val card = Card(CardShape.HEART, CardNumber.ACE)
        player.receiveCard(card)
        beforeCount + 1 shouldBe player.handsCardCount
    }
})
