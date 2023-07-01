package blackjack.domain.gamer

import blackjack.domain.card.CardFixture
import blackjack.domain.card.cards
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "초기화를 하지 않았다면 hit가 불가능하다" {
        val player = player()
        player.canHit() shouldBe false
    }

    "hit 상태라면 hit가 가능하다" {
        val player = player()
        player.init(cards(CardFixture.heartQueen, CardFixture.heartTwo)) // q(10) + 2 = 12
        player.canHit() shouldBe true
    }

    "bust가 되었다면 hit가 불가능하다" {
        val player = player()
        player.init(cards(CardFixture.heartQueen, CardFixture.heartTwo)) // q(10) + 2 = 12
        player.hit(CardFixture.heartKing) // 12 + k(10) = 22
        player.canHit() shouldBe false
    }

    "stay를 했다면 hit가 불가능하다" {
        val player = player()
        player.init(cards(CardFixture.heartQueen, CardFixture.heartTwo))
        player.stay()
        player.canHit() shouldBe false
    }

    "플레이어의 카드 목록을 캡쳐할 수 있다" {
        val name = "test"
        val player = player(name)
        val cards = cards(CardFixture.heartQueen, CardFixture.heartTwo)
        player.init(cards)
        player.captureCards() shouldBe PlayerCards(name, cards)
    }
})
