package blackjack.domain

import domain.player.Dealer
import domain.player.Player
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class DealerTest : FreeSpec({
    val dealer = Dealer()

    "딜러는 카드 덱을 가지고 있다" {
        dealer.deck.shouldNotBeNull()
    }

    "딜러는 플레이어한테 카드를 줄 수 있다" {
        val player = Player("ep")
        val beforeCardCount = player.handsCardCount
        dealer.giveCard(player = player)
        beforeCardCount + 1 shouldBe player.handsCardCount
    }
})
