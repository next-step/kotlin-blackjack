package blackjack.domain.game

import blackjack.domain.player.playerNames
import blackjack.domain.score.CardScoreCalculator
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : BehaviorSpec({

    Given("게임이 처음 만들어 졌을 때") {
        val game = blackJackGame(playerNames("test1", "test2"))

        When("카드를 분배하면") {
            val cardDistributionResult = game.distributeCardsToPlayers()

            Then("플레이어들은 2장씩 카드를 받는다") {
                cardDistributionResult.countOfCardDistribution shouldBe 2
            }
        }
    }

    Given("카드 분배가 완료 되었을 때") {
        val playerNames = playerNames("test1", "test2")
        val game = blackJackGame(playerNames)
        game.distributeCardsToPlayers()

        When("현재 턴을 확인하면") {
            val turn = game.currentTurn() as BlackJackGameTurn.PlayerAnswer

            Then("대기중인 첫 플레이어의 hit 대답을 기다린다") {
                turn shouldBe BlackJackGameTurn.PlayerAnswer(playerNames[0])
            }
        }
    }

    Given("플레이어의 대답을 기다리고 있을 때") {
        val playerNames = playerNames("test1", "test2")

        When("hit 답변을 하면") {
            val game = blackJackGame(playerNames)
            val cardDistributeResult = game.distributeCardsToPlayers()
            val hitResult = game.hitFocusedPlayer()

            Then("한장의 카드를 발급 받는다") {
                val initCardsSize = cardDistributeResult.playerCards[0].cards.size
                val cardSize = hitResult.cards.size
                cardSize shouldBe initCardsSize + 1
            }
        }

        When("stay 답변을 하면") {
            val game = blackJackGame(playerNames)
            game.distributeCardsToPlayers()
            game.stayFocusedPlayer()

            Then("다음 플레이어로 순서가 넘어간다") {
                val turn = game.currentTurn() as BlackJackGameTurn.PlayerAnswer
                turn.playerName shouldBe playerNames[1]
            }
        }
    }

    Given("플레이어가 hit 대답을 했을 때") {
        val playerNames = playerNames("test1", "test2")
        val game = blackJackGame(playerNames)
        game.distributeCardsToPlayers()

        When("bust 상태가 되면") {
            var isBust = false
            while (isBust.not()) {
                val playerCards = game.hitFocusedPlayer().cards
                isBust = CardScoreCalculator.calculateScore(playerCards).isBust
            }

            Then("다음 플레이어로 순서가 넘어간다") {
                val turn = (game.currentTurn() as BlackJackGameTurn.PlayerAnswer)
                val playerName = turn.playerName
                playerName shouldBe playerNames[1]
            }
        }
    }

    Given("2명의 플레이어가 참여 했을 때") {
        val playerNames = playerNames("test1", "test2")
        val game = blackJackGame(playerNames)

        When("2명 모두 카드 받기를 완료하면") {
            game.distributeCardsToPlayers()
            game.stayFocusedPlayer()
            game.stayFocusedPlayer()

            Then("게임이 종료된다.") {
                game.currentTurn().isFinished() shouldBe true
            }
        }
    }
})
