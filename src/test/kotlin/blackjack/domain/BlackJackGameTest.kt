package blackjack.domain

import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.domain.player.Players
import blackjack.mock.InputProcessorMock
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : DescribeSpec({
    describe("게임 생성") {
        context("게임에 참여할 2명의 이름 전달") {
            val name1 = "Hong"
            val name2 = "Kim"
            val game = BlackJackGame(
                inputProcessor = InputProcessorMock(
                    playerNames = listOf(name1, name2)
                ),
            )

            it("전달된 이름으로 플레이어 세팅") {
                game.players shouldBe Players(listOf(Player(PlayerName(name1)), Player(PlayerName(name2))))
            }
        }
    }

    describe("모든 플레이어에게 카드 배분") {
        val game = BlackJackGame(InputProcessorMock())
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

    describe("이번 차례 플레이어에게 카드 1장 배분") {
        val game = BlackJackGame(InputProcessorMock())
        val handCount = game.players.playerInTurn.hand.cards.size
        val deckCount = game.dealer.deck.cards.size
        context("카드 배분") {
            game.dealCardToPlayerInTurn()

            it("플레이어는 카드 1장 수령") {
                game.players.playerInTurn.hand.cards.count() shouldBe handCount + 1
            }

            it("덱에서는 카드 1장 제거") {
                game.dealer.deck.cards.size shouldBe deckCount - 1
            }
        }
    }
})
