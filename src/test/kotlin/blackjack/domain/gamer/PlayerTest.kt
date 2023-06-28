package blackjack.domain.gamer

import blackjack.domain.card.cards
import blackjack.domain.card.heartKing
import blackjack.domain.card.heartQueen
import blackjack.domain.card.heartTwo
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "초기화를 하지 않았다면 hit가 불가능하다" {
        val player = player("test")
        player.canHit() shouldBe false
    }

    "hit 상태라면 hit가 가능하다" {
        val player = player("test")
        player.init(cards(heartQueen(), heartTwo())) // q(10) + 2 = 12
        player.canHit() shouldBe true
    }

    "bust가 되었다면 hit가 불가능하다" {
        val player = player("test")
        player.init(cards(heartQueen(), heartTwo())) // q(10) + 2 = 12
        player.hit(heartKing()) // 12 + k(10) = 22
        player.canHit() shouldBe false
    }

    "stay를 했다면 hit가 불가능하다" {
        val player = player("test")
        player.init(cards(heartQueen(), heartTwo()))
        player.stay()
        player.canHit() shouldBe false
    }

    "플레이어의 카드 목록을 캡쳐할 수 있다" {
        val name = PlayerName("test")
        val player = Player(name)
        val cards = cards(heartQueen(), heartTwo())
        player.init(cards)
        player.captureCards() shouldBe PlayerCards(name, cards)
    }
})
