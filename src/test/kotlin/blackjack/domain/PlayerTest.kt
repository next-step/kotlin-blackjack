package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerTest : BehaviorSpec({
    Given("`Name`을 가진다") {
        val name = "철수"

        When("이름이 `$name` 일때") {
            val player = Player.from(name)

            Then("이름은 `$name` 이어야 한다") {
                player.name shouldBe name
            }
        }
    }

    Given("`Card`를 받을 수 있다") {
        val player = Player.from("철수")
        val cards = listOf(Card("A", Suit.SPADE))

        When("카드를 받을 때") {
            player.receive(cards)

            Then("카드가 추가되어야 한다") {
                player.totalCards shouldBe cards
            }
        }
    }

    Given("`Bust` 상태를 알 수 있다") {
        val player = Player.from("철수")
        val cards =
            listOf(
                Card("A", Suit.SPADE),
                Card("A", Suit.SPADE),
                Card("K", Suit.SPADE),
            )

        When("카드가 ${cards.joinToString { "${it.rank} ${it.suit.displayName}" }} 일때 카드 숫자를 합쳐 21을 초과하는지 알 수 있다") {
            player.receive(cards)

            Then("Bust 상태여야 한다") {
                player.isBust shouldBe true
            }
        }
    }
})
