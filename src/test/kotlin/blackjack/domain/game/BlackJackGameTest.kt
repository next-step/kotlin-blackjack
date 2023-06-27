package blackjack.domain.game

import blackjack.domain.card.CardDenomination
import blackjack.domain.card.CardHolder
import blackjack.domain.card.heartCard
import blackjack.domain.gamer.playerNames
import blackjack.domain.score.CardScoreCalculator
import blackjack.domain.shuffle.ForceMoveForwardCardShuffler
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : BehaviorSpec({

    Given("게임이 처음 만들어 졌을 때") {
        val game = blackJackGame(playerNames("test1", "test2"))

        When("카드를 분배하면") {
            val cardDistributionResult = game.distributeCardsToPlayers()

            Then("플레이어들은 2장씩 카드를 받는다") {
                cardDistributionResult.distributionCardSize shouldBe 2
            }

            Then("딜러는 2장의 카드를 받는다") {
                cardDistributionResult.dealerCards.size shouldBe 2
            }

            Then("딜러는 2장의 카드 중 첫번째 카드는 오픈 처리가 되어있다") {
                (cardDistributionResult.dealerCards.first() is CardHolder.Open) shouldBe true
            }

            Then("딜러는 2장의 카드 중 두번째 카드는 숨김 처리가 되어있다") {
                (cardDistributionResult.dealerCards.last() is CardHolder.Hide) shouldBe true
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

            Then("딜러의 순서로 넘어간다") {
                (game.currentTurn() is BlackJackGameTurn.Dealer) shouldBe true
            }
        }
    }

    Given("카드의 합이 16 이하인 딜러의 차례가 되었을 때") {
        val playerNames = playerNames("test1")
        val game = blackJackGame(
            shuffler = ForceMoveForwardCardShuffler(
                heartCard(CardDenomination.TWO),
                heartCard(CardDenomination.THREE),
                heartCard(CardDenomination.FOUR),
                heartCard(CardDenomination.FIVE),
            ),
            playerNames = playerNames,
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
        val playerNames = playerNames("test1")
        val game = blackJackGame(
            shuffler = ForceMoveForwardCardShuffler(
                heartCard(CardDenomination.TEN),
                heartCard(CardDenomination.JACK),
                heartCard(CardDenomination.QUEEN),
                heartCard(CardDenomination.KING),
            ),
            playerNames = playerNames,
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
        val playerNames = playerNames("test1")
        val game = blackJackGame(
            shuffler = ForceMoveForwardCardShuffler(
                heartCard(CardDenomination.TEN),
                heartCard(CardDenomination.JACK),
                heartCard(CardDenomination.QUEEN),
                heartCard(CardDenomination.KING),
            ),
            playerNames = playerNames,
        )
        game.distributeCardsToPlayers() // 딜러 : [10, 10] 플레이어 [10, 10]
        game.stayFocusedPlayer()
        game.executeDealerTurn() // 딜러는 [10, 10] 추가 발급 받지 않음
        When("결과를 만들면") {
            val result = game.makeGameResult()

            Then("딜러와 플레이어들의 카드를 반환한다") {
                result.dealerGameResult.dealerCards shouldBe listOf(
                    heartCard(CardDenomination.TEN),
                    heartCard(CardDenomination.JACK),
                )
                result.playerGameResults[0].playerCards.cards shouldBe listOf(
                    heartCard(CardDenomination.QUEEN),
                    heartCard(CardDenomination.KING),
                )
            }
        }
    }

    Given("1명의 참가자와 딜러가 있는 게임에서 딜러가 이겼을 떄") {
        val game = blackJackGame(
            shuffler = ForceMoveForwardCardShuffler(
                heartCard(CardDenomination.TEN), // 딜러
                heartCard(CardDenomination.JACK), // 딜러
                heartCard(CardDenomination.TWO), // 참가자
                heartCard(CardDenomination.THREE), // 참가자
            ),
            playerNames = playerNames("test1"),
        )
        game.distributeCardsToPlayers() // 딜러 : [10, 10] 플레이어 [2, 3]
        game.stayFocusedPlayer() // 참가자 받지 않음
        game.executeDealerTurn() // 딜러는 추가 발급 받지 않음

        When("결과를 만들면") {
            val result = game.makeGameResult()

            Then("딜러의 결과는 1승 0무 0패이다") {
                result.matchResult.dealerMatchResult shouldBe DealerMatchResult(1, 0, 0)
            }

            Then("참가자의 결과는 패배다") {
                result.matchResult.playerMatchResults[0].matchResultType shouldBe MatchResultType.LOSE
            }
        }
    }

    Given("1명의 참가자와 딜러가 있는 게임에서 참가자가 이겼을 떄") {
        val game = blackJackGame(
            shuffler = ForceMoveForwardCardShuffler(
                heartCard(CardDenomination.SEVEN), // 딜러
                heartCard(CardDenomination.TEN), // 딜러
                heartCard(CardDenomination.QUEEN), // 참가자
                heartCard(CardDenomination.JACK), // 참가자
            ),
            playerNames = playerNames("test1"),
        )
        game.distributeCardsToPlayers() // 딜러 : [7, 10] 플레이어 [10, 10]
        game.stayFocusedPlayer() // 참가자 받지 않음
        game.executeDealerTurn() // 딜러는 추가 발급 받지 않음

        When("결과를 만들면") {
            val result = game.makeGameResult()

            Then("딜러의 결과는 0승 0무 1패이다") {
                result.matchResult.dealerMatchResult shouldBe DealerMatchResult(0, 0, 1)
            }

            Then("참가자의 결과는 승리이다") {
                result.matchResult.playerMatchResults[0].matchResultType shouldBe MatchResultType.WIN
            }
        }
    }

    Given("1명의 참가자와 딜러가 있는 게임에서 딜러와 참가자가 비겼을 떄") {
        val game = blackJackGame(
            shuffler = ForceMoveForwardCardShuffler(
                heartCard(CardDenomination.TEN), // 딜러
                heartCard(CardDenomination.JACK), // 딜러
                heartCard(CardDenomination.QUEEN), // 참가자
                heartCard(CardDenomination.KING), // 참가자
            ),
            playerNames = playerNames("test1"),
        )
        game.distributeCardsToPlayers() // 딜러 : [10, 10] 플레이어 [10, 10]
        game.stayFocusedPlayer() // 참가자 받지 않음
        game.executeDealerTurn() // 딜러는 추가 발급 받지 않음

        When("결과를 만들면") {
            val result = game.makeGameResult()

            Then("딜러의 결과는 0승 1무 0패이다") {
                result.matchResult.dealerMatchResult shouldBe DealerMatchResult(0, 1, 0)
            }

            Then("참가자의 결과는 무승부이다") {
                result.matchResult.playerMatchResults[0].matchResultType shouldBe MatchResultType.TIE
            }
        }
    }

    Given("1명의 참가자와 딜러가 있는 게임에서 딜러가 버스트 되었을 때") {
        val game = blackJackGame(
            shuffler = ForceMoveForwardCardShuffler(
                heartCard(CardDenomination.TEN), // 딜러
                heartCard(CardDenomination.THREE), // 딜러
                heartCard(CardDenomination.QUEEN), // 참가자
                heartCard(CardDenomination.KING), // 참가자
                heartCard(CardDenomination.JACK), // 딜러 추가분
            ),
            playerNames = playerNames("test1"),
        )
        game.distributeCardsToPlayers() // 딜러 : [10, 3] 플레이어 [10, 10]
        game.stayFocusedPlayer() // 참가자 받지 않음
        game.executeDealerTurn() // 딜러 추가 발급 받음

        When("결과를 만들면") {
            val result = game.makeGameResult()

            Then("딜러의 결과는 0승 0무 1패이다") {
                result.matchResult.dealerMatchResult shouldBe DealerMatchResult(0, 0, 1)
            }

            Then("참가자의 결과는 승리이다") {
                result.matchResult.playerMatchResults[0].matchResultType shouldBe MatchResultType.WIN
            }
        }
    }
})
