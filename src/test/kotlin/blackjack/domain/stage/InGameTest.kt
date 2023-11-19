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
                val stage = InGameStage(game)
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

    describe("다음 게임 스테이지 처리") {
        context("플레이어가 아직 게임을 진행하지 않았다면") {
            val players = Players(
                listOf(
                    Player(PlayerName("currentPlayer"), Hand()),
                    Player(PlayerName("anotherPlayer"), Hand()),
                )
            )
            val game = BlackJackGame(InputProcessorMock(playerAction = PlayerAction.HIT), players = players)
            val stage = InGameStage(game)

            it("게임의 다음 상태는 현재 플레이어의 InGame이 된다") {
                stage.nextStage().shouldBeTypeOf<InGameStage>()
                game.playerInTurn.name shouldBe PlayerName("currentPlayer")
            }
        }


        context("플레이어가 카드 한 장을 더 달라고 한 경우") {
            context("카드를 받은 후 플레이어의 카드의 합이 21이 넘는다면") {
                val score21Cards =
                    mutableListOf(Card(Suit.SPADE, Rank.KING), Card(Suit.SPADE, Rank.KING), Card(Suit.DIAMOND, Rank.ACE))
                val players = Players(
                    listOf(
                        Player(PlayerName("currentPlayer"), Hand(score21Cards)),
                        Player(PlayerName("anotherPlayer"), Hand()),
                    )
                )

                val game = BlackJackGame(InputProcessorMock(playerAction = PlayerAction.HIT), players = players)
                val stage = InGameStage(game)
                stage.progress()

                it("게임의 다음 상태는 DetermineWinnerStage가 된다") {
                    stage.nextStage().shouldBeTypeOf<DetermineWinnerStage>()
                }
            }

            context("카드를 받은 후 플레이어 카드의 합이 21이 넘지 않는다면") {
                val score0Cards = mutableListOf<Card>()
                val players = Players(
                    listOf(
                        Player(PlayerName("currentPlayer"), Hand(score0Cards)),
                        Player(PlayerName("anotherPlayer"), Hand()),
                    )
                )

                val game = BlackJackGame(InputProcessorMock(playerAction = PlayerAction.HIT), players = players)
                val stage = InGameStage(game)
                stage.progress()

                it("게임의 다음 상태는 해당 플레이어가 진행하는 InGame이 된다") {
                    stage.nextStage().shouldBeTypeOf<InGameStage>()
                    game.playerInTurn.name shouldBe PlayerName("currentPlayer")
                }
            }
        }

        context("플레이어가 카드를 안받는다고 한 경우") {
            val players = Players(
                listOf(
                    Player(PlayerName("currentPlayer"), Hand()),
                    Player(PlayerName("nextPlayer"), Hand())
                )
            )

            val game = BlackJackGame(InputProcessorMock(playerAction = PlayerAction.STAND), players = players)

            context("첫 번째 플레이어가 진행한 경우") {
                val stage = InGameStage(game)
                stage.progress()

                it("게임의 다음 상태는 다음 플레이어가 진행하는 InGame이 된다") {
                    stage.nextStage().shouldBeTypeOf<InGameStage>()
                    game.playerInTurn.name shouldBe PlayerName("nextPlayer")
                }
            }

            context("두 번째 플레이어가 진행한 경우") {
                val stage = InGameStage(game)
                stage.progress()

                it("게임의 다음 상태는 DetermineWinnerStage가 된다") {
                    stage.nextStage().shouldBeTypeOf<DetermineWinnerStage>()
                }
            }
        }
    }
})
