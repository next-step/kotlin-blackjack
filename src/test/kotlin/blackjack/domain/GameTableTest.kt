package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.card.Hand
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.player.DealerPlayer
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.domain.player.Players
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class GameTableTest : DescribeSpec({
    describe("dealToAll") {
        context("카드가 모두 한 장도 없는 상태일 때") {
            val dealer = Dealer()
            val players = Players(
                listOf(
                    Player(PlayerName("kim"), { Action.HIT }, Hand()),
                    Player(PlayerName("lee"), { Action.HIT }, Hand()),
                )
            )
            val table = GameTable(dealer, players)

            it("모두 두장씩 배분") {
                table.dealToAll(2)

                table.dealer.hand.cards.size shouldBe 2
                table.players.all.forEach { player ->
                    player.hand.cards.size shouldBe 2
                }
            }
        }
    }

    describe("dealToPlayerInTurn") {
        val dealer = Dealer()
        val deckCount = dealer.deck.cards.size
        val players = Players(
            listOf(
                Player(PlayerName("kim"), { Action.HIT }, Hand()),
                Player(PlayerName("lee"), { Action.HIT }, Hand()),
            )
        )
        val table = GameTable(dealer, players)

        context("카드 배분") {
            table.dealToPlayerInTurn(1)

            it("플레이어는 카드 1장 수령") {
                val playerInTurn = players.inTurn
                playerInTurn.hand.cards.count() shouldBe 1
            }

            it("덱에서는 카드 1장 제거") {
                dealer.deck.cards.size shouldBe deckCount - 1
            }
        }
    }

    describe("passPlayerTurn") {
        val players = listOf(
            Player(PlayerName("kim"), { Action.HIT }, Hand()),
            Player(PlayerName("lee"), { Action.HIT }, Hand()),
        ).let(::Players)
        val table = GameTable(Dealer(), players)

        context("플레이어 1이 차례인 경우") {
            players.inTurn shouldBe players.all.first()

            table.passPlayerTurn()

            it("플레이어 2에게 차례가 넘어감") {
                players.inTurn shouldBe players.all.last()
            }
        }

        context("플레이어 2가 차례인 경우") {
            players.inTurn shouldBe players.all.last()

            it("턴이 넘어갈 수 없다") {
                shouldThrowExactly<IllegalArgumentException> {
                    table.passPlayerTurn()
                }
            }
        }
    }

    describe("dealToDealer") {
        val cards = mutableListOf(
            Card(Suit.CLUB, Rank.ACE),
            Card(Suit.CLUB, Rank.TWO),
            Card(Suit.DIAMOND, Rank.THREE),
            Card(Suit.DIAMOND, Rank.FOUR),
        )
        val dealer = Dealer(Deck(ArrayDeque(cards)))
        val deckCount = dealer.deck.cards.size
        val players = Players(
            listOf(
                Player(PlayerName("kim"), { Action.HIT }, Hand()),
                Player(PlayerName("lee"), { Action.HIT }, Hand()),
            )
        )
        val table = GameTable(dealer, players)
        val count = 2
        context("딜러 카드가 한 장도 없을 때 카드 ${count}장 배분") {
            dealer.hand.cards.size shouldBe 0

            table.dealToDealer(2)

            it("딜러는 ${count}장 카드 수령") {
                dealer.hand.cards.size shouldBe count
            }

            it("덱에서는 카드 ${count}장 제거") {
                dealer.deck.cards.size shouldBe deckCount - count
            }
        }
    }

    describe("isLastPlayerTurn") {
        val players = listOf(
            Player(PlayerName("kim"), { Action.HIT }, Hand()),
            Player(PlayerName("lee"), { Action.HIT }, Hand()),
        )
        val table = GameTable(Dealer(), Players(players))
        context("첫 번째 플레이어 턴") {
            table.players.inTurn shouldBe players.first()

            it("false 반환") {
                table.isLastPlayerTurn shouldBe false
            }
        }
        context("마지막 플레이어 턴") {
            table.passPlayerTurn()
            table.players.inTurn shouldBe players.last()

            it("true 반환") {
                table.isLastPlayerTurn shouldBe true
            }
        }
    }

    describe("playerInTurn") {
        val players = listOf(
            Player(PlayerName("kim"), { Action.HIT }, Hand()),
            Player(PlayerName("lee"), { Action.HIT }, Hand()),
        )
        val table = GameTable(Dealer(), Players(players))
        context("첫 번째 플레이어 턴") {
            table.players.inTurn shouldBe players.first()

            it("첫 번째 플레이어 반환") {
                table.playerInTurn shouldBe players.first()
            }
        }
        context("마지막 플레이어 턴") {
            table.passPlayerTurn()
            table.players.inTurn shouldBe players.last()

            it("두 번째 플레이어 반환") {
                table.playerInTurn shouldBe players.last()
            }
        }
    }

    describe("playerInTurnAction") {
        context("이번 턴의 플레이어가 HIT를 하면") {
            val players = listOf(
                Player(PlayerName("kim"), { Action.HIT }, Hand()),
                Player(PlayerName("lee"), { Action.HIT }, Hand()),
            )
            val table = GameTable(Dealer(), Players(players))
            table.playerInTurn shouldBe players.first()

            it("HIT 반환") {
                val result = table.playerInTurnAction

                result shouldBe Action.HIT
            }
        }

        context("이번 턴의 플레이어가 STAND를 하면") {
            val players = listOf(
                Player(PlayerName("kim"), { Action.STAND }, Hand()),
                Player(PlayerName("lee"), { Action.HIT }, Hand()),
            )
            val table = GameTable(Dealer(), Players(players))
            table.playerInTurn shouldBe players.first()

            it("STAND 반환") {
                val result = table.playerInTurnAction

                result shouldBe Action.STAND
            }
        }
    }

    describe("dealerAction") {
        val players = listOf(
            Player(PlayerName("kim"), { Action.HIT }, Hand()),
            Player(PlayerName("lee"), { Action.HIT }, Hand()),
        )
        context("딜러가 HIT를 하면") {
            val under16ScoreCards = mutableListOf(
                Card(Suit.SPADE, Rank.TWO),
                Card(Suit.SPADE, Rank.THREE),
            )
            val dealer = Dealer(player = DealerPlayer(Hand(under16ScoreCards)))
            val table = GameTable(dealer, Players(players))
            dealer.hitOrStand() shouldBe Action.HIT

            it("HIT 반환") {
                val result = table.dealerAction

                result shouldBe Action.HIT
            }
        }

        context("딜러가 STAND를 하면") {
            val over16ScoreCards = mutableListOf(
                Card(Suit.SPADE, Rank.QUEEN),
                Card(Suit.SPADE, Rank.QUEEN),
            )
            val dealer = Dealer(player = DealerPlayer(Hand(over16ScoreCards)))
            val table = GameTable(dealer, Players(players))
            dealer.hitOrStand() shouldBe Action.STAND

            it("STAND 반환") {
                val result = table.dealerAction

                result shouldBe Action.STAND
            }
        }
    }
})
