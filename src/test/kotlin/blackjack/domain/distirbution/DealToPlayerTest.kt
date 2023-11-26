package blackjack.domain.distirbution

import blackjack.domain.Action
import blackjack.domain.BlackJackGame
import blackjack.domain.Dealer
import blackjack.domain.GameTable
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

class DealToPlayerTest : DescribeSpec({
    describe("DealToPlayer") {
        context("플레이어가 HIT을 하면") {
            val players = Players(
                listOf(
                    Player(PlayerName("currentPlayer"), { Action.HIT }, Hand()),
                    Player(PlayerName("nextPlayer"), { Action.HIT }, Hand())
                )
            )
            val table = GameTable(Dealer(), players)
            val game = BlackJackGame(InputProcessorMock(action = Action.HIT), table = table)
            val deckCount = game.table.dealer.deck.cards.size
            val handCount = game.table.players.inTurn.hand.cards.size

            val dealToPlayer = DealToPlayer()
            game.setDistributor(dealToPlayer)

            val result = dealToPlayer(game.table) { distributor -> game.setDistributor(distributor) }

            it("플레이어의 카드는 증가") {
                game.table.players.inTurn.hand.cards.size shouldBe handCount + 1
            }

            it("덱에서 카드는 제거") {
                game.table.dealer.deck.cards.size shouldBe deckCount - 1
            }

            it("결과 값의 플레이어는 이번 차례 진행한 플레이어") {
                result.player.name shouldBe PlayerName("currentPlayer")
            }

            it("게임 룰에 의한 STAND인지 여부는 FALSE") {
                result.isSystemStand shouldBe false
            }
        }

        context("플레어가 STAND를 하면") {
            val cards = mutableListOf(Card(Suit.HEART, Rank.TEN), Card(Suit.HEART, Rank.TEN))
            val players = Players(
                listOf(
                    Player(PlayerName("currentPlayer"), { Action.STAND }, Hand(cards)),
                    Player(PlayerName("nextPlayer"), { Action.STAND }, Hand())
                )
            )

            val table = GameTable(Dealer(), players)
            val game = BlackJackGame(InputProcessorMock(), table = table)
            context("첫번째 플레이어가 STAND을 한 경우") {
                val dealToPlayer = DealToPlayer()
                game.setDistributor(dealToPlayer)

                val result = dealToPlayer(game.table) { distributor -> game.setDistributor(distributor) }

                it("게임의 다음 상태는 다음 플레이어 배분 차례") {
                    game.dealCards.shouldBeTypeOf<DealToPlayer>()
                    game.table.players.inTurn.name shouldBe PlayerName("nextPlayer")
                }

                it("플레이어 카드는 변화 없음") {
                    game.table.players.all.first().hand.cards shouldBe cards
                }

                it("결과 값의 플레이어는 이번 차례 진행한 플레이어") {
                    result.player.name shouldBe PlayerName("currentPlayer")
                }

                it("게임 룰에 의한 STAND인지 여부는 FALSE") {
                    result.isSystemStand shouldBe false
                }
            }

            context("카드 배분시 두 번째 플레이어가 STAND을 한 경우") {
                game.table.players.inTurn.name shouldBe PlayerName("nextPlayer")
                val dealToPlayer = DealToPlayer()
                game.setDistributor(dealToPlayer)

                val result = dealToPlayer(game.table) { distributor -> game.setDistributor(distributor) }

                it("딜러 카드 배분 차례") {
                    game.dealCards.shouldBeTypeOf<DealToDealer>()
                }

                it("결과 값의 플레이어는 이번 차례 진행한 플레이어") {
                    result.player.name shouldBe PlayerName("nextPlayer")
                }

                it("게임 룰에 의한 STAND인지 여부는 FALSE") {
                    result.isSystemStand shouldBe false
                }
            }

            context("카드 배분시 게임 룰에 의한 (21점 이상) STAND을 한 경우") {
                val cards = mutableListOf(Card(Suit.HEART, Rank.TEN), Card(Suit.HEART, Rank.TEN), Card(Suit.HEART, Rank.TEN))
                val players = Players(
                    listOf(
                        Player(PlayerName("currentPlayer"), { Action.HIT }, Hand(cards)),
                        Player(PlayerName("nextPlayer"), { Action.STAND }, Hand())
                    )
                )

                val table = GameTable(Dealer(), players)
                val game = BlackJackGame(InputProcessorMock(), table = table)

                game.table.players.inTurn.name shouldBe PlayerName("currentPlayer")
                val dealToPlayer = DealToPlayer()
                game.setDistributor(dealToPlayer)

                val result = dealToPlayer(game.table) { distributor -> game.setDistributor(distributor) }

                it("게임의 다음 상태는 다음 플레이어 배분 차례") {
                    game.dealCards.shouldBeTypeOf<DealToPlayer>()
                    game.table.players.inTurn.name shouldBe PlayerName("nextPlayer")
                }

                it("플레이어 카드는 변화 없음") {
                    game.table.players.all.first().hand.cards shouldBe cards
                }

                it("결과 값의 플레이어는 이번 차례 진행한 플레이어") {
                    result.player.name shouldBe PlayerName("currentPlayer")
                }

                it("게임 룰에 의한 STAND인지 여부는 TRUE") {
                    result.isSystemStand shouldBe true
                }
            }
        }
    }
})
