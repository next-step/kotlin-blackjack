package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayersTest : BehaviorSpec({
    Given("`Player`를 가진다") {
        val players = Players.from(listOf("player1", "player2"))

        Then("player의 수는 2이다") {
            players.size shouldBe 2
        }
    }

    Given("`Player`에게 카드를 나눠 줄 수 있다") {
        listOf(
            Player.from("player1") to listOf(Card("A", Suit.SPADE), Card("2", Suit.DIAMOND)),
            Player.from("player2") to listOf(Card("3", Suit.HEART), Card("4", Suit.CLUB)),
        ).forEach { (player, cards) ->
            When("`${player.name}`에게 카드를 나눠 준다") {
                val players = Players.from(listOf("player1", "player2"))
                players.deal(player, cards)

                Then("`$player`은 $cards 를 가진다") {
                    players.findCardOf(player) shouldBe cards
                }
            }
        }
    }
})
