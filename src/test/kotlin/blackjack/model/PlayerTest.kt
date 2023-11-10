package blackjack.model

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldHave

class PlayerTest : StringSpec({
    "플레이어는 dealing 시 2장의 카드를 받을 수 있다" {
        shouldNotThrow<IllegalArgumentException> {
            val player = Player("구글")
            player.deal(Pack.anyCard(), Pack.anyCard())
            player.cards.count() shouldBe 2
        }
    }

    "플레이어는 hit 시 1장의 카드를 받을 수 있다" {
        shouldNotThrow<IllegalArgumentException> {
            val player = Player("애플")
            player.hit(Pack.anyCard())
            player.cards.count() shouldBe 1
        }
    }
})
