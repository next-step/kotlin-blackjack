package blackjack.domain

import blackJack.model.Dealer
import blackJack.model.Player
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DealerSpec : BehaviorSpec({
    given("딜러와 플레이어 2명이 있을떄") {
        val dealer = Dealer("dealer")
        val player1 = Player("player1")
        val player2 = Player("player2")
        val players = listOf(player1, player2)

        `when`("게임을 시작했을때") {
            dealer.startGame(players)

            then("플레이어는 딜러가 나눠준 카드를 두장씩 갖고 있다.") {
                players[0].hand.size shouldBe 2
                players[1].hand.size shouldBe 2
            }
        }
    }

    given("딜러에게 카드덱이 주어지고") {
        val dealer = Dealer("dealer")

        `when`("딜러가 게임을 시작했을때") {
            val players = listOf(Player("player1"), Player("player2"))
            dealer.startGame(players)

            then("플레이어는 딜러가 나눠준 카드를 두장씩 갖고 있다.") {
                players[0].hand.size shouldBe 2
                players[1].hand.size shouldBe 2
            }
        }

        `when`("딜러에게 카드를 한장 나눠줬을때") {
            val prevCount = dealer.countCard()
            dealer.drawCard()
            val currentCount = dealer.countCard()

            then("카드덱에는 한장의 카드가 사라졌다.") {
                currentCount shouldBe prevCount - 1
            }
        }
    }
})
