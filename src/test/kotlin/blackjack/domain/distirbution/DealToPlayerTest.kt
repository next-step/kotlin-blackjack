package blackjack.domain.distirbution

import blackjack.domain.Action
import blackjack.domain.card.Rank
import blackjack.domain.player.PlayerName
import blackjack.mock.blackJackGame
import blackjack.mock.card
import blackjack.mock.hand
import blackjack.mock.player
import blackjack.mock.players
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class DealToPlayerTest : DescribeSpec({
    describe("DealToPlayer") {
        context("플레이어가 HIT (player: HIT, 점수 : bust 아님) 을 하면") {
            val players = players(player("currentPlayer", Action.HIT), player())
            val game = blackJackGame(inputAction = Action.HIT, players = players)
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

        context("모든 플레이어는 항상 STAND를 응답(player: STAND)할 때") {
            val player1Cards = hand(card(Rank.TEN), card(Rank.TEN))
            val players = players(
                player("currentPlayer", Action.STAND, player1Cards),
                player("nextPlayer", Action.STAND),
            )
            val game = blackJackGame(players = players)

            context("첫번째 플레이어가 STAND을 하면") {
                val dealToPlayer = DealToPlayer()
                game.setDistributor(dealToPlayer)
                val result = dealToPlayer(game.table) { distributor -> game.setDistributor(distributor) }

                it("게임의 다음 상태는 다음 플레이어 배분 차례") {
                    game.dealCards.shouldBeTypeOf<DealToPlayer>()
                    game.table.players.inTurn.name shouldBe PlayerName("nextPlayer")
                }

                it("플레이어 카드는 변화 없음") {
                    game.table.players.all.first().hand shouldBe player1Cards
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
                val cards = hand(card(Rank.TEN), card(Rank.TEN), card(Rank.TEN))
                val players = players(player("currentPlayer", hand = cards, action = Action.HIT), player("nextPlayer"))

                val game = blackJackGame(players = players)
                game.table.players.inTurn.name shouldBe PlayerName("currentPlayer")
                val dealToPlayer = DealToPlayer()
                game.setDistributor(dealToPlayer)

                val result = dealToPlayer(game.table) { distributor -> game.setDistributor(distributor) }

                it("게임의 다음 상태는 다음 플레이어 배분 차례") {
                    game.dealCards.shouldBeTypeOf<DealToPlayer>()
                    game.table.players.inTurn.name shouldBe PlayerName("nextPlayer")
                }

                it("플레이어 카드는 변화 없음") {
                    game.table.players.all.first().hand shouldBe cards
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
