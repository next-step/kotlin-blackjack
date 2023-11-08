package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerTest : BehaviorSpec({
    given("플레이어 이름이 주어지면") {
        val name = "원동재"
        `when`("플레이어 생성을 하면") {
            val player = Player(name)
            then("플레이어의 이름은 원동재가 된다.") {
                player.name shouldBe "원동재"
            }
        }
    }

    given("플레이어 이름과 카드가 2장 주어지면") {
        val name = "원동재"
        val cards = listOf(Card(Suit.SPADE, Rank.ACE), Card(Suit.HEART, Rank.TWO))
        `when`("플레이어 생성을 하면") {
            val player = Player(name, cards)
            then("플레이어의 이름은 원동재가 된다.") {
                player.name shouldBe "원동재"
            }
            then("플레이어의 카드는 2장이 된다.") {
                player.cards.size shouldBe 2
            }
        }
    }
})
