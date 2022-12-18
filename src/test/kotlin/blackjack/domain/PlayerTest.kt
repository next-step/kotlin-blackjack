package blackjack.domain

import domain.Card
import domain.CardNumber
import domain.CardShape
import domain.Player
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class PlayerTest : FreeSpec({

    "플레이어는 카드를 들고 있을 수 있다" {
        val player = Player("ep")
        player.hands.shouldNotBeNull()
    }

    "플레이어는 손의 든 카드의 숫자를 알 수 있다" {
        val player = Player("ep")
        player.handsCardCount.shouldNotBeNull()
    }

    "플레이어는 카드를 받을 수 있다" {
        val player = Player("ep")
        val beforeCount = player.handsCardCount
        val card = Card(CardShape.HEART, CardNumber.ACE)
        player.receiveCard(card)
        beforeCount + 1 shouldBe player.handsCardCount
    }

    "플레이어는 카드 점수를 계산할 수 있다" {
        val player = Player("ep")
        val card = Card(CardShape.HEART, CardNumber.ACE)
        player.receiveCard(card)
        player.handsCardScore() shouldBe 1
    }

    "플레이어는 ACE 카드 점수를 선택할 수 있다" {
        val player = Player("ep")
        val card = Card(CardShape.HEART, CardNumber.ACE)
        player.receiveCard(card)
        player.handsCardCountWithAceHighScore() shouldBe 11
    }
})
