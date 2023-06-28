package blackjack.domain.gamer

import blackjack.domain.card.CardDeck
import blackjack.domain.card.cards
import blackjack.domain.card.heartFour
import blackjack.domain.card.heartKing
import blackjack.domain.card.heartQueen
import blackjack.domain.card.heartTen
import blackjack.domain.card.spadeFour
import blackjack.domain.card.spadeQueen
import blackjack.domain.card.spadeTen
import blackjack.domain.card.spadeTwo
import blackjack.domain.game.DealerMatchResult
import blackjack.domain.shuffle.CardNotShuffler
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayersTest : BehaviorSpec({

    Given("플레이어 목록이 만들어 졌을 때") {
        val players = Players.create(playerNames("test1", "test2"))

        When("카드를 배분하지 않았다면") {
            Then("플레이어들은 카드를 갖고 있지 않다") {
                players.notHasCards() shouldBe true
            }
        }
    }

    Given("플레이어들에게 카드를 배분하지 않았을 때") {
        val players = Players.create(playerNames("test1", "test2"))

        When("대기중 플레이어를 가져오면") {
            Then("RuntimeException 예외 처리를 한다") {
                shouldThrow<RuntimeException> {
                    players.requireWaitPlayer()
                }
            }
        }
    }

    Given("2명의 플레이어가 존재할 때 ") {
        val playerNames = playerNames("test1", "test2")

        When("카드를 배분 했다면") {
            val cardDeck = CardDeck.create(CardNotShuffler())
            val players = Players.create(playerNames)
            players.init { cards(cardDeck.pick(), cardDeck.pick()) }

            Then("대기중인 플레이어는 첫번째 플레이어다") {
                players.requireWaitPlayer().name shouldBe playerNames[0]
            }
        }

        When("첫번째 플레이어가 스테이 했다면") {
            val cardDeck = CardDeck.create(CardNotShuffler())
            val players = Players.create(playerNames)
            players.init { cards(cardDeck.pick(), cardDeck.pick()) }
            players.requireWaitPlayer().stay()

            Then("대기중인 플레이어는 두번째 플레이어다") {
                players.requireWaitPlayer().name shouldBe playerNames[1]
            }
        }
    }

    Given("플레이어에게 카드를 배분 했을 때") {
        val playerNames = playerNames("test1", "test2")
        val cardDeck = CardDeck.create(CardNotShuffler())
        val cards1 = cards(cardDeck.pick(), cardDeck.pick())
        val cards2 = cards(cardDeck.pick(), cardDeck.pick())
        val cards = mutableListOf(cards1, cards2)
        val players = Players.create(playerNames)
        players.init { cards.removeFirst() }

        When("캡쳐를 하면") {
            val captures = players.captureAllPlayerCards()

            Then("플레이어들의 카드 목록을 반환한다") {
                captures[0] shouldBe PlayerCards(playerNames[0], cards1)
                captures[1] shouldBe PlayerCards(playerNames[1], cards2)
            }
        }
    }

    Given("플레이어의 점수보다 딜러의 점수가 높을 떄") {
        val players = players("test1").apply {
            init { cards(heartTen(), heartFour()) } // 10 + 4 = 14
        }
        val dealer = Dealer().apply {
            init(cards(heartQueen(), heartKing())) // 10 + 10 = 20
        }
        When("승패 결과를 확인하면") {
            val matchResult = players.match(dealer)

            Then("플레이어는 패배다") {
                matchResult.gamerMatchResult.playerMatchResults[0].matchResultType.isLose() shouldBe true
            }

            Then("딜러는 1승 0무 0패이다") {
                matchResult.gamerMatchResult.dealerMatchResult shouldBe DealerMatchResult(1, 0, 0)
            }
        }
    }

    Given("플레이어의 점수보다 딜러의 점수가 같을 떄") {
        val players = players("test1").apply {
            init { cards(heartTen(), heartFour()) } // 10 + 4 = 14
        }
        val dealer = Dealer().apply {
            init(cards(spadeTen(), spadeFour())) // 10 + 4 = 14
        }

        When("승패 결과를 확인하면") {
            val matchResult = players.match(dealer)

            Then("플레이어는 무승부이다") {
                matchResult.gamerMatchResult.playerMatchResults[0].matchResultType.isTie() shouldBe true
            }

            Then("딜러는 0승 1무 0패이다") {
                matchResult.gamerMatchResult.dealerMatchResult shouldBe DealerMatchResult(0, 1, 0)
            }
        }
    }

    Given("플레이어의 점수보다 딜러의 점수가 낮을 떄") {
        val players = players("test1").apply {
            init { cards(heartTen(), heartFour()) } // 10 + 4 = 14
        }
        val dealer = Dealer().apply {
            init(cards(spadeTen(), spadeTwo())) // 10 + 2 = 12
        }

        When("승패 결과를 확인하면") {
            val matchResult = players.match(dealer)

            Then("플레이어는 승리다") {
                matchResult.gamerMatchResult.playerMatchResults[0].matchResultType.isWin() shouldBe true
            }

            Then("딜러는 0승 0무 1패이다") {
                matchResult.gamerMatchResult.dealerMatchResult shouldBe DealerMatchResult(0, 0, 1)
            }
        }
    }

    Given("플레이어는 stay, 딜러는 bust 일 때") {
        val players = players("test1").apply {
            init { cards(heartTen(), heartFour()) } // 10 + 4 = 14
            requireWaitPlayer().stay()
        }

        val dealer = Dealer().apply {
            init(cards(spadeTen(), spadeTwo())) // 10 + 2 = 12
            hit(spadeQueen()) // 12 + 10 = 22
        }

        When("승패 결과를 확인하면") {
            val matchResult = players.match(dealer)

            Then("플레이어는 승리다") {
                matchResult.gamerMatchResult.playerMatchResults[0].matchResultType.isWin() shouldBe true
            }

            Then("딜러는 0승 0무 1패이다") {
                matchResult.gamerMatchResult.dealerMatchResult shouldBe DealerMatchResult(0, 0, 1)
            }
        }
    }

    Given("플레이어는 bust, 딜러는 stay 일 때") {
        val players = players("test1").apply {
            init { cards(heartTen(), heartFour()) } // 10 + 4 = 14
            requireWaitPlayer().hit(heartQueen()) // 14 + 10 = 24
        }

        val dealer = Dealer().apply {
            init(cards(spadeTen(), spadeTwo())) // 10 + 2 = 12
            stay()
        }

        When("승패 결과를 확인하면") {
            val matchResult = players.match(dealer)

            Then("플레이어는 패배다") {
                matchResult.gamerMatchResult.playerMatchResults[0].matchResultType.isLose() shouldBe true
            }

            Then("딜러는 1승 0무 0패이다") {
                matchResult.gamerMatchResult.dealerMatchResult shouldBe DealerMatchResult(1, 0, 0)
            }
        }
    }

    Given("플레이어와 딜러 모두 bust 일 때") {
        val players = players("test1").apply {
            init { cards(heartTen(), heartFour()) } // 10 + 4 = 14
            requireWaitPlayer().hit(heartQueen()) // 14 + 10 = 24
        }

        val dealer = Dealer().apply {
            init(cards(spadeTen(), spadeTwo())) // 10 + 2 = 12
            hit(spadeQueen()) // 12 + 10 = 22
        }

        When("승패 결과를 확인하면") {
            val matchResult = players.match(dealer)

            Then("플레이어는 패배다") {
                matchResult.gamerMatchResult.playerMatchResults[0].matchResultType.isLose() shouldBe true
            }

            Then("딜러는 1승 0무 0패이다") {
                matchResult.gamerMatchResult.dealerMatchResult shouldBe DealerMatchResult(1, 0, 0)
            }
        }
    }
})
