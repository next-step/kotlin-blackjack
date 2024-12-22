package domain

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Deck
import blackjack.domain.Game
import blackjack.domain.GameMembers
import blackjack.domain.HitCommand
import blackjack.domain.Participant.Dealer
import blackjack.domain.Participant.Player
import blackjack.domain.ParticipantStatus
import blackjack.domain.Result
import blackjack.domain.Suit
import fixture.CardListFixture
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class GameTest : DescribeSpec({
    lateinit var players: List<Player>
    lateinit var dealer: Dealer
    lateinit var sut: Game

    beforeTest {
        dealer = Dealer(Deck(CardListFixture.simpleCardList()))
        players = listOf(Player("pobi", 1_000), Player("jason", 1_000))
        val members = GameMembers(players, dealer)
        sut = Game(members)
    }

    describe("all players") {
        it("딜러를 포함한 모든 플레이어를 조회한다.") {
            val actual = sut.allPlayers()
            actual.members.size shouldBe 3
        }
    }

    describe("onlyPlayers") {
        it("딜러를 제외한 플레이어만 조회한다") {
            val actual = sut.participants()
            actual.members.size shouldBe 2
        }
    }

    describe("init test") {
        it("게임을 시작하면 각 플레이어들에게 카드를 2장씩 나누어준다.") {
            sut.allPlayers().members[0].ownedCards.size shouldBe 2
            sut.allPlayers().members[1].ownedCards.size shouldBe 2
        }
    }

    describe("processPlayerTurn Test") {
        lateinit var player: Player
        beforeTest { player = Player("pobi", 1_000) }

        context("`command`가 `HIT`인 경우") {
            it("카드를 한장 받는다.") {
                sut.processPlayerTurn(player, HitCommand.HIT)
                player.ownedCards.size shouldBe 1
            }
        }

        context("`command`가 `STAY`인 경우") {
            it("카드를 받지 않는다.") {
                sut.processPlayerTurn(player, HitCommand.STAY)
                player.ownedCards.size shouldBe 0
            }
        }
    }

    describe("Player 상태를 기준으로 완료 여부를 결정한다.") {
        context("Player가 stay를 원하는 경우") {
            it("should be ture") {
                val pobi = sut.allPlayers().members[0]
                val actual = sut.isPlayerStillPlaying(pobi)
                actual shouldBe true
            }
        }

        context("Player가 hit을 원하는 경우") {
            it("should be true") {
                val pobi = sut.allPlayers().members[0]
                val actual = sut.isPlayerStillPlaying(pobi)
                actual shouldBe true
            }
        }
    }

    describe("isPlayerDone test") {
        context("플레이어의 카드 합이 21이 넘는경우") {
            it("should be false") {
                val cards =
                    mutableListOf(
                        Card(Suit.SPADES, CardNumber.TEN),
                        Card(Suit.SPADES, CardNumber.QUEEN),
                        Card(Suit.SPADES, CardNumber.KING),
                    )
                val pobi = Player("pobi", 1_000, cards)
                val actual = sut.isPlayerStillPlaying(pobi)
                actual shouldBe false
            }
        }

        context("플레이어가 stay를 원하는 경우") {
            it("should be false") {
                val cards =
                    mutableListOf(
                        Card(Suit.SPADES, CardNumber.TEN),
                        Card(Suit.SPADES, CardNumber.QUEEN),
                    )
                val pobi = Player("pobi", 1_000, cards)
                pobi.stay()
                val actual = sut.isPlayerStillPlaying(pobi)
                actual shouldBe false
            }
        }

        context("플레이어의 카드 합이 21이하이고, stay를 하지 않은 경우") {
            it("should be true") {
                val cards =
                    mutableListOf(
                        Card(Suit.SPADES, CardNumber.TEN),
                        Card(Suit.SPADES, CardNumber.QUEEN),
                    )
                val pobi = Player("pobi", 1_000, cards)
                val actual = sut.isPlayerStillPlaying(pobi)
                actual shouldBe true
            }
        }
    }

    describe("isDealerCardSumLessThan16 Test") {
        context("16 이하인 경우") {
            it("should be true") {
                val fixedDealer =
                    Dealer(
                        Deck(
                            mutableListOf(
                                Card(Suit.SPADES, CardNumber.TWO),
                                Card(Suit.SPADES, CardNumber.THREE),
                                Card(Suit.SPADES, CardNumber.FOUR),
                                Card(Suit.SPADES, CardNumber.FIVE),
                            ),
                        ),
                    )
                val fixedParticipants = listOf(Player("pobi", 1_000))
                val gameMembers = GameMembers(fixedParticipants, fixedDealer)
                sut = Game(gameMembers)
                val actual = sut.isDealerDrawCard()
                actual shouldBe true
            }
        }

        context("16 초과인 경우") {
            it("should be false") {
                val fixedDealer =
                    Dealer(
                        Deck(
                            mutableListOf(
                                Card(Suit.SPADES, CardNumber.TEN),
                                Card(Suit.CLUBS, CardNumber.TEN),
                                Card(Suit.DIAMONDS, CardNumber.TEN),
                                Card(Suit.HEARTS, CardNumber.TEN),
                            ),
                        ),
                    )
                val fixedParticipants = listOf(Player("pobi", 1_000))
                val gameMembers = GameMembers(fixedParticipants, fixedDealer)
                sut = Game(gameMembers)
                val actual = sut.isDealerDrawCard()
                actual shouldBe false
            }
        }
    }

    describe("dealerHit test") {
        it("딜러의 패에 한장의 카드를 추가한다.") {
            val fixedDealer =
                Dealer(
                    Deck(
                        mutableListOf(
                            Card(Suit.SPADES, CardNumber.TEN),
                            Card(Suit.CLUBS, CardNumber.TEN),
                            Card(Suit.DIAMONDS, CardNumber.TEN),
                            Card(Suit.HEARTS, CardNumber.TEN),
                            Card(Suit.HEARTS, CardNumber.NINE),
                        ),
                    ),
                )
            val fixedParticipants = listOf(Player("pobi", 1_000))
            val gameMembers = GameMembers(fixedParticipants, fixedDealer)
            sut = Game(gameMembers)

            sut.giveCardToDealer()

            fixedDealer.ownedCards.size shouldBe 3
        }
    }

    describe("딜러의 승률을 계산한다.") {
        lateinit var player1: Player
        lateinit var player2: Player
        lateinit var player3: Player
        lateinit var player4: Player
        lateinit var sut: Game

        beforeTest {
            player1 = Player("player1", 1_000)
            player2 = Player("player2", 1_000)
            player3 = Player("player3", 1_000)
            player4 = Player("player4", 1_000)
            val fixedDealer = Dealer(Deck(CardListFixture.mixedCardList()))
            val fixedParticipants = listOf(player1, player2, player3, player4)
            sut = Game(GameMembers(fixedParticipants, fixedDealer))
        }

        context("딜러의 카드 합이 플레이어보다 크거나 같으면") {
            it("딜러가 승리한다.") {
                val actual = sut.determineDealerWinningOutcome()
                actual.results.filter { it == Result.WIN }.size shouldBe 1
            }
        }

        context("딜러의 카드 합이 플레이어보다 작으면") {
            it("패배한다.") {
                val actual = sut.determineDealerWinningOutcome()
                actual.results.filter { it == Result.LOSE }.size shouldBe 3
            }
        }
    }

    describe("isPlayerStillPlaying test") {
        lateinit var player1: Player
        lateinit var sut: Game

        context("플레이어의 상태가 PLAYING인 경우") {
            beforeTest {
                player1 = Player("player1", 1_000)
                val fixedDealer = Dealer(Deck(CardListFixture.mixedCardList()))
                val fixedParticipants = listOf(player1)
                sut = Game(GameMembers(fixedParticipants, fixedDealer))
            }
            it("should be true") {
                player1.status shouldBe ParticipantStatus.PLAYING
                sut.isPlayerStillPlaying(player1) shouldBe true
            }
        }

        context("플레이어의 상태가 PLAYING이 아닌 경우") {
            beforeTest {
                val cards =
                    mutableListOf(
                        Card(Suit.SPADES, CardNumber.TEN),
                        Card(Suit.SPADES, CardNumber.QUEEN),
                        Card(Suit.SPADES, CardNumber.KING),
                    )
                player1 = Player("player1", 1_000, cards)
                val fixedDealer = Dealer(Deck(CardListFixture.mixedCardList()))
                val fixedParticipants = listOf(player1)
                sut = Game(GameMembers(fixedParticipants, fixedDealer))
            }

            it("should be false") {
                player1.status shouldBe ParticipantStatus.BUSTED
                sut.isPlayerStillPlaying(player1) shouldBe false
            }
        }
    }
})
