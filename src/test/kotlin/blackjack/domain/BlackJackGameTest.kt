package blackjack.domain

import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.domain.player.PlayerNames
import blackjack.domain.player.Players
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : DescribeSpec({
    describe("게임 생성") {
        context("게임에 참여할 2명의 이름 전달") {
            val name1 = PlayerName("Hong")
            val name2 = PlayerName("Kim")
            val game = BlackJackGame(PlayerNames(listOf(name1, name2)))

            it("전달된 이름으로 플레이어 세팅") {
                game.players shouldBe Players(listOf(Player(name1), Player(name2)))
            }
        }
    }

    describe("모든 플레이어에게 카드 배분") {
        val game = BlackJackGame(PlayerNames(listOf(PlayerName("hong"), PlayerName("kim"))))
        context("카드 배분") {
            val count = 2
            game.dealCardsToAllPlayers(count)

            it("플레이어들은 카드 수령") {
                game.players.allPlayers.forEach { player ->
                    player.hand.cards.size shouldBe count
                }
            }

            it("덱에서는 카드 제거") {
                game.dealer.deck.cards.size shouldBe 52 - count * 2
            }
        }
    }
})
