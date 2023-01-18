package blackjack.application

import application.BlackJackGame
import domain.player.Dealer
import domain.player.Participants
import domain.player.Player
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class BlackJackGameTest : BehaviorSpec({
    given("블랙잭 게임은 딜러와 참여자가 있어야 만들 수 있다") {
        val dealer = Dealer()
        val playerA = Player("ep")
        val playerB = Player("kim")
        val participants = Participants(listOf(playerA, playerB))

        val blackJackGame = BlackJackGame(dealer = dealer, players = participants)
        blackJackGame.shouldNotBeNull()

        `when`("게임이 시작하면") {
            blackJackGame.init()
            then("플레이어는 카드를 두장씩 받는다") {
                playerA.hands.count shouldBe 2
            }
        }

        `when`("플레이어는") {
            blackJackGame.receiveCard(playerA)
            then("카드를 한 장씩 받을 수 있다") {
                playerA.hands.count shouldBe 3
            }
        }

        `when`("모든 플레이어의 정보를") {
            val allPlayers = blackJackGame.allPlayers()
            then("확인할 수 있다") {
                allPlayers.size shouldBe 2
            }
        }

        `when`("플레이어의 카드 패 정보를") {
            then("확인할 수 있다") {
                playerA.hands.count shouldBe 3
                playerB.hands.count shouldBe 2
            }
        }
    }
})
