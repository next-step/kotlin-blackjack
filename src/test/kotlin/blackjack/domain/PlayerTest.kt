package blackjack.domain

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import domain.card.Cards
import domain.player.Player
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.booleans.shouldNotBeTrue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class PlayerTest : FreeSpec({

    "플레이어는 카드를 들고 있을 수 있다" {
        val player = Player("ep")
        player.hands.shouldNotBeNull()
    }

    "플레이어는 손의 든 카드의 숫자를 알 수 있다" {
        val player = Player("ep")
        player.hands.count.shouldNotBeNull()
    }

    "플레이어는 카드를 받을 수 있다" {
        val cards = listOf(
            Card(CardShape.HEART, CardNumber.ACE)
        )
        val player = Player("ep", Cards(cards = cards, limitReceiveScore = 21))
        val beforeCount = player.hands.count

        val card = Card(CardShape.HEART, CardNumber.TWO)
        player.receiveCard(card)
        beforeCount + 1 shouldBe player.hands.count
    }

    "플레이어는 카드 점수를 계산할 수 있다" {
        val cards = listOf(
            Card(CardShape.HEART, CardNumber.ACE)
        )
        val player = Player("ep", Cards(cards = cards, limitReceiveScore = 21))
        player.handsCardScore() shouldBe 11
    }

    "ACE 21에 맞는 숫자로 변경된다." - {

        "21과 가까운 11로 변경된다." {
            val cards = listOf(
                Card(CardShape.HEART, CardNumber.ACE),
                Card(CardShape.DIAMOND, CardNumber.JACK)
            )
            val player = Player("ep", Cards(cards = cards, limitReceiveScore = 21))
            player.handsCardScore() shouldBe 21
        }

        "21과 가까운 1로 변경된다." {

            val cards = listOf(
                Card(CardShape.DIAMOND, CardNumber.JACK),
                Card(CardShape.HEART, CardNumber.ACE),
                Card(CardShape.DIAMOND, CardNumber.QUEEN)
            )

            val player = Player("ep2", Cards(cards = cards, limitReceiveScore = 21))
            player.handsCardScore() shouldBe 21
        }

        "ACE 가 여러장일 때 " {
            val cards = listOf(
                Card(CardShape.HEART, CardNumber.ACE),
                Card(CardShape.DIAMOND, CardNumber.ACE),
                Card(CardShape.CLOVER, CardNumber.ACE)
            )
            val player = Player("ep3", hands = Cards(cards = cards, limitReceiveScore = 21))
            player.handsCardScore() shouldBe 13
        }
    }

    "플레이어는 카드를 더 받을 수 있는지 확인할 수 있다" - {

        "21보다 작으면 플레이어는 카드를 더 받을 수 있다" {
            val cards = listOf(
                Card(CardShape.HEART, CardNumber.JACK),
                Card(CardShape.DIAMOND, CardNumber.QUEEN)
            )
            val player = Player("ep3", hands = Cards(cards = cards, limitReceiveScore = 21))
            player.isAvailableReceive().shouldBeTrue()
        }

        "21이 넘어가면 플레이어는 카드를 더 받을 수 없다" {
            val cards = listOf(
                Card(CardShape.HEART, CardNumber.JACK),
                Card(CardShape.DIAMOND, CardNumber.QUEEN),
                Card(CardShape.CLOVER, CardNumber.TWO)
            )
            val player = Player("ep3", hands = Cards(cards = cards, limitReceiveScore = 21))
            player.isAvailableReceive().shouldNotBeTrue()
        }
    }
})
