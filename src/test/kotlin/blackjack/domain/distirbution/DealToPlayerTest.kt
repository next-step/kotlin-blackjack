package blackjack.domain.distirbution

import blackjack.domain.Action
import blackjack.domain.card.Rank
import blackjack.domain.player.PlayerName
import blackjack.mock.card
import blackjack.mock.hand
import blackjack.mock.player
import blackjack.mock.players
import blackjack.mock.table
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class DealToPlayerTest : DescribeSpec({
    describe("deal") {
        context("플레이어가 HIT (player: HIT, 점수 : 21점 미만) 을 하면") {
            val players = players(player("currentPlayer", Action.HIT), player())
            val table = table(inputAction = Action.HIT, players = players)
            val deckCount = table.dealer.deck.cards.size
            val handCount = table.players.inTurn.hand.cards.size

            val dealToPlayer = DealToPlayer(table)

            val result = dealToPlayer.deal()

            it("플레이어의 카드 1장이 증가한다") {
                table.players.inTurn.hand.cards.size shouldBe handCount + 1
            }

            it("덱에서 카드 1장은 제거된다") {
                table.dealer.deck.cards.size shouldBe deckCount - 1
            }

            it("결과 값에 반환된 플레이어는 이번 차례 진행한 플레이어다") {
                result.player.name shouldBe PlayerName("currentPlayer")
            }

            it("플레이어가 HIT을 한 결과이므로 게임 룰에 의한 STAND인지 여부는 FALSE") {
                result.isSystemStand shouldBe false
            }
        }

        context("플레이어가 STAND를 응답(player: STAND)했을 때") {
            val player1Cards = hand(card(Rank.TEN), card(Rank.TEN))
            val players = players(
                player("currentPlayer", Action.STAND, player1Cards),
                player("nextPlayer", Action.STAND),
            )
            val table = table(inputAction = Action.HIT, players = players)

            context("첫번째 플레이어의 STAND였다면") {
                val dealToPlayer = DealToPlayer(table)
                val result = dealToPlayer.deal()

                it("게임의 다음 배분은 DealToPlayer 다") {
                    dealToPlayer.nextDistributor.shouldBeTypeOf<DealToPlayer>()
                    table.players.inTurn.name shouldBe PlayerName("nextPlayer")
                }

                it("플레이어 카드는 변화 없다") {
                    table.players.value.first().hand shouldBe player1Cards
                }

                it("결과 값에 반환된 플레이어는 이번 차례 진행한 플레이어다") {
                    result.player.name shouldBe PlayerName("currentPlayer")
                }

                it("플레이어가 STAND를 했으므로 게임 룰에 의한 STAND인지 여부는 FALSE이다") {
                    result.isSystemStand shouldBe false
                }
            }

            context("두 번째 플레이어의 STAND였다면") {
                table.players.inTurn.name shouldBe PlayerName("nextPlayer")
                val dealToPlayer = DealToPlayer(table)
                val result = dealToPlayer.deal()

                it("게임의 다음 배분은 DealToDealer 다") {
                    dealToPlayer.nextDistributor.shouldBeTypeOf<DealToDealer>()
                }

                it("결과 값에 반환된 플레이어는 이번 차례 진행한 플레이어다") {
                    result.player.name shouldBe PlayerName("nextPlayer")
                }

                it("플레이어가 STAND를 했으므로 게임 룰에 의한 STAND인지 여부는 FALSE다") {
                    result.isSystemStand shouldBe false
                }
            }
        }

        context("첫번째 플레이어가 게임 룰에 의한 (21점 이상) STAND인 경우") {
            val cards = hand(card(Rank.TEN), card(Rank.ACE))
            val players = players(player("currentPlayer", hand = cards, action = Action.HIT), player("nextPlayer"))

            val table = table(inputAction = Action.HIT, players = players)
            table.players.inTurn.name shouldBe PlayerName("currentPlayer")
            val dealToPlayer = DealToPlayer(table)

            val result = dealToPlayer.deal()

            it("게임의 다음 배분은 DealToPlayer 다") {
                dealToPlayer.nextDistributor.shouldBeTypeOf<DealToPlayer>()
                table.players.inTurn.name shouldBe PlayerName("nextPlayer")
            }

            it("플레이어 카드는 변화 없다") {
                table.players.value.first().hand shouldBe cards
            }

            it("결과 값에 반환된 플레이어는 이번 차례 진행한 플레이어다") {
                result.player.name shouldBe PlayerName("currentPlayer")
            }

            it("게임 룰에 의한 STAND인지 여부는 TRUE다") {
                result.isSystemStand shouldBe true
            }
        }
    }
})
