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
        context("카드 배분시 플레이어가 HIT을 하면") {
            val game = BlackJackGame(InputProcessorMock(action = Action.HIT))
            val deckCount = game.table.dealer.deck.cards.size
            val handCount = game.table.players.inTurn.hand.cards.size

            val dealToPlayer = DealToPlayer()
            game.setDistributor(dealToPlayer)
            dealToPlayer(game.table) { distributor -> game.setDistributor(distributor) }

            it("플레이어의 카드는 증가") {
                game.table.players.inTurn.hand.cards.size shouldBe handCount + 1
            }

            it("덱에서 카드는 제거") {
                game.table.dealer.deck.cards.size shouldBe deckCount - 1
            }
        }

        context("카드 배분시 첫번째 플레이어가 STAND을 한 경우") {
            val cards = mutableListOf(Card(Suit.HEART, Rank.TEN), Card(Suit.HEART, Rank.TEN))
            val players = Players(
                listOf(
                    Player(PlayerName("currentPlayer"), { Action.STAND }, Hand(cards)),
                    Player(PlayerName("nextPlayer"), { Action.STAND }, Hand())
                )
            )

            val table = GameTable(Dealer(), players)
            val game = BlackJackGame(InputProcessorMock(), table = table)

            context("카드 배분시 첫번째 플레이어가 STAND을 한 경우") {
                val dealToPlayer = DealToPlayer()
                game.setDistributor(dealToPlayer)
                dealToPlayer(game.table) { distributor -> game.setDistributor(distributor) }

                it("게임의 다음 상태는 다음 플레이어 배분 차례") {
                    game.dealCards.shouldBeTypeOf<DealToPlayer>()
                    game.table.players.inTurn.name shouldBe PlayerName("nextPlayer")
                }

                it("플레이어 카드는 변화 없음") {
                    game.table.players.all.first().hand.cards shouldBe cards
                }
            }

            context("카드 배분시 두 번째 플레이어가 STAND을 한 경우") {
                game.table.players.inTurn.name shouldBe PlayerName("nextPlayer")
                val dealToPlayer = DealToPlayer()
                game.setDistributor(dealToPlayer)
                dealToPlayer(game.table) { distributor -> game.setDistributor(distributor) }

                it("딜러 카드 배분 차례") {
                    game.dealCards.shouldBeTypeOf<DealToDealer>()
                }
            }
        }
    }
})
