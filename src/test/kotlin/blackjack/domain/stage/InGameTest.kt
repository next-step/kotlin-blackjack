package blackjack.domain.stage

import blackjack.domain.BlackJackGame
import blackjack.domain.PlayerAction
import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.domain.player.Players
import blackjack.mock.InputProcessorMock
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class InGameTest : DescribeSpec({

    describe("게임 진행") {
        context("게임 진행 단계에서 플레이어가 한 장의 카드를 더 받겠다고 한 경우") {
            val game = BlackJackGame(InputProcessorMock(playerAction = PlayerAction.HIT))
            val deckCount = game.dealer.deck.cards.size
            val handCount = game.players.playerInTurn.hand.cards.size

            context("게임 진행 스테이지를 진행시키면") {
                val stage = InGame(game)
                stage.progress()

                it("플레이어의 카드는 증가") {
                    game.players.playerInTurn.hand.cards.size shouldBe handCount + 1
                }

                it("덱에서 카드는 제거") {
                    game.dealer.deck.cards.size shouldBe deckCount - 1
                }
            }
        }
    }

    describe("카드 지급시 점수 초과로 인한 게임 종료") {
        context("플레이어가 카드 한 장을 더 달라고 한 경우") {
            val score21Cards =
                mutableListOf(Card(Suit.SPADE, Rank.KING), Card(Suit.SPADE, Rank.KING), Card(Suit.DIAMOND, Rank.ACE))
            val players = Players(
                listOf(
                    Player(PlayerName("currentPlayer"), Hand(score21Cards)),
                    Player(PlayerName("anotherPlayer"), Hand(mutableListOf()))
                )
            )

            val game = BlackJackGame(InputProcessorMock(playerAction = PlayerAction.HIT), players = players)

            context("카드를 받은 후 플레이어의 카드의 합이 21이 넘는다면") {
                val stage = InGame(game)
                stage.progress()

                it("게임의 다음 상태는 End가 된다") {
                    stage.nextStage().shouldBeTypeOf<End>()
                }
            }
        }
    }
})
