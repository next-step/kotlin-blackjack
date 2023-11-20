package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.domain.player.Players
import blackjack.mock.InputProcessorMock
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

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

    describe("현재 플레이어의 점수가 최대 첨수 초과했는지 여부 반환") {
        context("현재 플레어가 최대 점수 21점을 초과했다면") {
            val playerOverMax =
                Player(
                    PlayerName("currentPlayer"), Hand(
                        mutableListOf(
                            Card(Suit.DIAMOND, Rank.TEN),
                            Card(Suit.DIAMOND, Rank.TEN),
                            Card(Suit.DIAMOND, Rank.TEN),
                        )
                    )
                )
            val game = BlackJackGame(
                InputProcessorMock(), players = Players(
                    listOf(
                        playerOverMax,
                        Player(PlayerName("otherPlayer"), Hand()),
                    )
                )
            )

            it("true 반환") {
                game.isPlayerInTurnScoreOverMax shouldBe true
            }
        }

        context("현재 플레어가 최대 점수를 초과하지 않았다면") {
            val playerUnderMax =
                Player(
                    PlayerName("currentPlayer"), Hand(
                        mutableListOf(
                            Card(Suit.DIAMOND, Rank.ACE),
                            Card(Suit.DIAMOND, Rank.TEN),
                        )
                    )
                )
            val game = BlackJackGame(
                InputProcessorMock(), players = Players(
                    listOf(
                        playerUnderMax,
                        Player(PlayerName("otherPlayer"), Hand()),
                    )
                )
            )
            it("false 반환") {
                game.isPlayerInTurnScoreOverMax shouldBe false
            }
        }
    }

    describe("다음 플레이어에게 차례 넘김") {
        val players = listOf(
            Player(PlayerName("kim"), Hand()),
            Player(PlayerName("lee"), Hand()),
        )
        val game = BlackJackGame(InputProcessorMock(), players = Players(players))

        context("플레이어 1이 차례인 경우") {
            game.playerInTurn shouldBe players.first()
            game.passTurnToNextPlayer()
            it("플레이어 2에게 차례가 넘어감") {
                game.playerInTurn shouldBe players.last()
            }
        }

        context("플레이어 2가 차례인 경우") {
            game.playerInTurn shouldBe players.last()
            it("턴이 넘어갈 수 없다") {
                shouldThrowExactly<IllegalArgumentException> {
                    game.passTurnToNextPlayer()
                }
            }
        }
    }

    describe("마지막 플레이어 턴인지 조회") {
        val players = listOf(
            Player(PlayerName("kim"), Hand()),
            Player(PlayerName("lee"), Hand()),
        )
        val game = BlackJackGame(InputProcessorMock(), players = Players(players))

        context("첫번쨰 플레이어 턴이라면") {
            game.playerInTurn shouldBe players.first()
            it("false 반환된다") {
                game.isLastTurn shouldBe false
            }
        }

        context("마지막 플레이어 턴이라면") {
            game.passTurnToNextPlayer()
            game.playerInTurn shouldBe players.last()

            it("true가 반환된다") {
                game.isLastTurn shouldBe true
            }
        }
    }
})
