package domain

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Game
import blackjack.domain.GameMembers
import blackjack.domain.HitCommand
import blackjack.domain.Participant.Player
import blackjack.domain.Participants
import blackjack.domain.Result
import blackjack.domain.Suit
import fixture.CardListFixture
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class GameTest : DescribeSpec({
    lateinit var participants: Participants
    lateinit var dealer: Dealer
    lateinit var sut: Game

    beforeTest {
        dealer = Dealer(Deck(CardListFixture.simpleCardList()))
        participants = Participants(listOf(Player("pobi"), Player("jason")))
        val members = GameMembers(participants, dealer)
        sut = Game(members)
    }

    describe("all players") {
        it("딜러를 포함한 모든 플레이어를 조회한다.") {
            val actual = sut.allPlayers()
            actual.allPlayers().size shouldBe 3
            actual.allPlayers()[0].name shouldBe "Dealer"
            actual.allPlayers()[1].name shouldBe "pobi"
            actual.allPlayers()[2].name shouldBe "jason"
        }
    }

    describe("onlyPlayers") {
        it("딜러를 제외한 플레이어만 조회한다") {
            val actual = sut.playersWithOutDealer()
            actual.allPlayers().size shouldBe 2
            actual.allPlayers()[0].name shouldBe "pobi"
            actual.allPlayers()[1].name shouldBe "jason"
        }
    }

    describe("init test") {
        it("게임을 시작하면 각 플레이어들에게 카드를 2장씩 나누어준다.") {
            participants.allPlayers()[0].ownedCards.size shouldBe 2
            participants.allPlayers()[1].ownedCards.size shouldBe 2
        }
    }

    describe("processPlayerTurn Test") {
        lateinit var player: Player
        beforeTest { player = Player("pobi") }

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
                val pobi = participants.allPlayers()[0]
                val actual = sut.isPlayerStillPlaying(pobi)
                actual shouldBe true
            }
        }

        context("Player가 hit을 원하는 경우") {
            it("should be true") {
                val pobi = participants.allPlayers()[0]
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
                val pobi = Player("pobi", cards)
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
                val pobi = Player("pobi", cards)
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
                val pobi = Player("pobi", cards)
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
                val fixedParticipants = Participants(listOf(Player("pobi")))
                val gameMembers = GameMembers(fixedParticipants, fixedDealer)
                sut = Game(gameMembers)
                val actual = sut.isDealerCardSumLessThan16()
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
                val fixedParticipants = Participants(listOf(Player("pobi")))
                val gameMembers = GameMembers(fixedParticipants, fixedDealer)
                sut = Game(gameMembers)
                val actual = sut.isDealerCardSumLessThan16()
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
            val fixedParticipants = Participants(listOf(Player("pobi")))
            val gameMembers = GameMembers(fixedParticipants, fixedDealer)
            sut = Game(gameMembers)

            sut.dealerHit()

            fixedDealer.ownedCards.size shouldBe 3
        }
    }

    describe("승패를 계산한다.") {
        lateinit var player1: Player
        lateinit var player2: Player
        lateinit var player3: Player
        lateinit var player4: Player
        lateinit var sut: Game

        beforeTest {
            player1 = Player("player1")
            player2 = Player("player2")
            player3 = Player("player3")
            player4 = Player("player4")
            val fixedDealer = Dealer(Deck(CardListFixture.mixedCardList()))
            val fixedParticipants = Participants(listOf(player1, player2, player3, player4))
            sut = Game(GameMembers(fixedParticipants, fixedDealer))
        }

        it("딜러의 카드보다 합이 큰 플레이어 이름을 리턴한다.") {
            player1.sumOfCard() shouldBe 17
            player2.sumOfCard() shouldBe 18
            player3.sumOfCard() shouldBe 19
            player4.sumOfCard() shouldBe 15
            val actual = sut.determineWinner()

            actual.filter { it.results == Result.WIN }.map { it.player.name } shouldContainExactly
                listOf(
                    "player1", "player2", "player3",
                )
        }

        it("dealer의 승리 횟수를 계산한다.") {
            val actual = sut.calculateDealerWinningScore()
            actual shouldBe 1
        }

        it("dealer의 패배 횟수를 계산한다.") {
            val actual = sut.calculateDealerLoseScore()
            actual shouldBe 3
        }
    }
})
