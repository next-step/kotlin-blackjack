package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest: StringSpec({

    "player는 go를 한다면 카드를 받을 수 있어야 한다" {
        val player = Player("test")
        player.go(Card.TWO)
        player.go(Card.THREE)

        player.cards shouldBe listOf(Card.TWO, Card.THREE)
    }

    "player는 stop을 한다면 자신의 점수를 계산해서 줘야한다" {
        val player = Player("test")
        player.go(Card.TWO)
        player.go(Card.THREE)

        player.stop() shouldBe 5
    }

    "player는 이름을 가질 수 있다" {
        val player = Player("edward")
        player.name shouldBe "edward"
    }
})
