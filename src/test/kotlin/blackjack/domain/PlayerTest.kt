package blackjack.domain

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import domain.player.Player
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
        player.handsCardScore() shouldBe 11
    }

    "ACE 21에 맞는 숫자로 변경된다." - {

        "21과 가까운 11로 변경된다." {
            val player = Player("ep")
            val aceCard = Card(CardShape.HEART, CardNumber.ACE)
            player.receiveCard(aceCard)
            val cardA = Card(CardShape.DIAMOND, CardNumber.JACK)
            player.receiveCard(cardA)
            player.handsCardScore() shouldBe 21
        }

        "21과 가까운 1로 변경된다." {
            val player = Player("ep2")
            val aceCard = Card(CardShape.HEART, CardNumber.ACE)
            player.receiveCard(aceCard)

            val cardA = Card(CardShape.DIAMOND, CardNumber.JACK)
            val cardB = Card(CardShape.DIAMOND, CardNumber.QUEEN)
            player.receiveCard(cardA)
            player.receiveCard(cardB)
            player.handsCardScore() shouldBe 21
        }

        "ACE 가 여러장일 때 " {
            val player = Player("ep3")
            val aceCardA = Card(CardShape.HEART, CardNumber.ACE)
            val aceCardB = Card(CardShape.DIAMOND, CardNumber.ACE)
            val aceCardC = Card(CardShape.CLOVER, CardNumber.ACE)
            player.receiveCard(aceCardA)
            player.receiveCard(aceCardB)
            player.receiveCard(aceCardC)
            player.handsCardScore() shouldBe 13
        }
    }
})
