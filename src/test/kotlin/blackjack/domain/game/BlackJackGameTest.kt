package blackjack.domain.game

import blackjack.domain.card.CardFixture
import blackjack.domain.card.cards
import blackjack.domain.gamer.DealerCard
import blackjack.domain.gamer.playerInitProperties
import blackjack.domain.score.CardScoreCalculator
import blackjack.domain.shuffle.ForceMoveForwardCardShuffler
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : BehaviorSpec({

    Given("게임이 처음 만들어 졌을 때") {
        val game = blackJackGame(playerInitProperties("test1", "test2"))

        When("카드를 분배하면") {
            val cardDistributionResult = game.distributeCardsToPlayers()

            Then("플레이어들은 2장씩 카드를 받는다") {
                cardDistributionResult.distributionCardSize shouldBe 2
            }

            Then("딜러는 2장의 카드를 받는다") {
                cardDistributionResult.dealerCards.size shouldBe 2
            }

            Then("딜러는 2장의 카드 중 첫번째 카드는 오픈 처리가 되어있다") {
                (cardDistributionResult.dealerCards.first() is DealerCard.Open) shouldBe true
            }

            Then("딜러는 2장의 카드 중 두번째 카드는 숨김 처리가 되어있다") {
                (cardDistributionResult.dealerCards.last() is DealerCard.Hide) shouldBe true
            }
        }
    }

    Given("카드 분배가 완료 되었을 때") {
        val playerInitProperties = playerInitProperties("test1", "test2")
        val game = blackJackGame(playerInitProperties)
        game.distributeCardsToPlayers()

        When("현재 턴을 확인하면") {
            val turn = game.currentTurn() as BlackJackGameTurn.PlayerAnswer

            Then("대기중인 첫 플레이어의 hit 대답을 기다린다") {
                turn shouldBe BlackJackGameTurn.PlayerAnswer(playerInitProperties.first().playerName)
            }
        }
    }

    Given("플레이어의 대답을 기다리고 있을 때") {
        val playerInitProperties = playerInitProperties("test1", "test2")

        When("hit 답변을 하면") {
            val game = blackJackGame(playerInitProperties)
            val cardDistributeResult = game.distributeCardsToPlayers()
            val hitResult = game.hitFocusedPlayer()

            Then("한장의 카드를 발급 받는다") {
                val initCardsSize = cardDistributeResult.playerCards.first().cards.value.size
                val cardSize = hitResult.cards.value.size
                cardSize shouldBe initCardsSize + 1
            }
        }

        When("stay 답변을 하면") {
            val game = blackJackGame(playerInitProperties)
            game.distributeCardsToPlayers()
            game.stayFocusedPlayer()

            Then("다음 플레이어로 순서가 넘어간다") {
                val turn = game.currentTurn() as BlackJackGameTurn.PlayerAnswer
                turn.playerName shouldBe playerInitProperties[1].playerName
            }
        }
    }

    Given("플레이어가 hit 대답을 했을 때") {
        val playerInitProperties = playerInitProperties("test1", "test2")
        val game = blackJackGame(playerInitProperties)
        game.distributeCardsToPlayers()

        When("bust 상태가 되면") {
            var isBust = false
            while (isBust.not()) {
                val playerCards = game.hitFocusedPlayer().cards.value
                isBust = CardScoreCalculator.calculateScore(playerCards).isBust
            }

            Then("다음 플레이어로 순서가 넘어간다") {
                val turn = (game.currentTurn() as BlackJackGameTurn.PlayerAnswer)
                val playerName = turn.playerName
                playerName shouldBe playerInitProperties[1].playerName
            }
        }
    }

    Given("2명의 플레이어가 참여 했을 때") {
        val game = blackJackGame(playerInitProperties("test1", "test2"))

        When("2명 모두 카드 받기를 완료하면") {
            game.distributeCardsToPlayers()
            game.stayFocusedPlayer()
            game.stayFocusedPlayer()

            Then("딜러의 순서로 넘어간다") {
                (game.currentTurn() is BlackJackGameTurn.Dealer) shouldBe true
            }
        }
    }

    Given("카드의 합이 16 이하인 딜러의 차례가 되었을 때") {
        val game = blackJackGame(
            playerInitProperties = playerInitProperties("test1"),
            shuffler = ForceMoveForwardCardShuffler(
                CardFixture.heartTwo,
                CardFixture.heartThree,
                CardFixture.heartFour,
                CardFixture.heartFive,
            ),
        )
        game.distributeCardsToPlayers() // 딜러 : [2, 3] 플레이어 [4, 5]
        game.stayFocusedPlayer()
        When("딜러의 턴을 실행하면") {
            val executeResult = game.executeDealerTurn() // 딜러는 [2, 3] 이므로 합은 5
            Then("1장의 카드를 추가로 받는다") {
                executeResult.isDistributedOneMoreCard shouldBe true
            }
        }
    }

    Given("카드의 합이 17 이상인 딜러의 차례가 되었을 때") {
        val game = blackJackGame(
            playerInitProperties = playerInitProperties("test1"),
            shuffler = ForceMoveForwardCardShuffler(
                CardFixture.heartTen,
                CardFixture.heartJack,
                CardFixture.heartQueen,
                CardFixture.heartKing,
            ),
        )
        game.distributeCardsToPlayers() // 딜러 : [10, 10] 플레이어 [10, 10]
        game.stayFocusedPlayer()
        When("딜러의 턴을 실행하면") {
            val executeResult = game.executeDealerTurn() // 딜러는 [10, 10] 이므로 합은 20
            Then("1장의 카드를 추가로 받지 않는다") {
                executeResult.isDistributedOneMoreCard shouldBe false
            }
        }
    }

    Given("게임 종료 턴이 되었을 때") {
        val game = blackJackGame(
            playerInitProperties = playerInitProperties("test1"),
            shuffler = ForceMoveForwardCardShuffler(
                CardFixture.heartTen,
                CardFixture.heartJack,
                CardFixture.heartQueen,
                CardFixture.heartKing,
            ),
        )
        game.distributeCardsToPlayers() // 딜러 : [10, 10] 플레이어 [10, 10]
        game.stayFocusedPlayer()
        game.executeDealerTurn() // 딜러는 [10, 10] 추가 발급 받지 않음
        When("결과를 만들면") {
            val result = game.makeGameResult()

            Then("딜러의 카드 목록을 반환한다") {
                val expected = cards(CardFixture.heartTen, CardFixture.heartJack)
                result.gamerCards.dealerCards shouldBe expected
            }

            Then("플레이어의 카드 목록을 반환한다") {
                val expected = cards(CardFixture.heartQueen, CardFixture.heartKing)
                result.gamerCards.allPlayerCards.first().cards shouldBe expected
            }
        }
    }
})
