package blackjack.domain.game

import blackjack.domain.player.playerNames
import blackjack.domain.score.CardScoreCalculator
import blackjack.domain.shuffle.CardNotShuffler
import blackjack.domain.shuffle.CardShuffler
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : BehaviorSpec({

    Given("카드를 분배하면") {
        val game = BlackJackGame(
            shuffler = CardNotShuffler(),
            playerNames = playerNames("test1", "test2"),
        )
        val cardDistributionResult = game.distributeCardsToPlayers()
        Then("플레이어들은 2장씩 카드를 받는다") {
            cardDistributionResult.countOfCardDistribution shouldBe 2
        }
    }

    Given("카드를 분배하고 나면") {
        val playerNames = playerNames("test1", "test2")
        val game = BlackJackGame(
            shuffler = CardNotShuffler(),
            playerNames = playerNames,
        )
        game.distributeCardsToPlayers()
        Then("대기중인 첫 플레이어의 hit 대답을 기다린다") {
            val turn = game.currentTurn() as BlackJackGameTurn.HitOrStay
            turn shouldBe BlackJackGameTurn.HitOrStay(playerNames[0])
        }
    }

    Given("플레이어가 hit 답변을 하면") {
        val playerNames = playerNames("test1")
        val game = BlackJackGame(
            shuffler = CardNotShuffler(),
            playerNames = playerNames,
        )
        val cardDistributionResult = game.distributeCardsToPlayers()
        val initCardsSize = cardDistributionResult.playerCardDeckCaptures[0].cards.size
        val hitResult = game.hitFocusedPlayer()
        Then("플레이어는 한장의 카드를 받는다") {
            hitResult.cards.size shouldBe initCardsSize + 1
        }
    }

    Given("플레이어가 bust 상태가 되면") {
        val playerNames = playerNames("test1", "test2")
        val game = BlackJackGame(
            shuffler = CardShuffler(),
            playerNames = playerNames,
        )
        game.distributeCardsToPlayers()
        var isBust = false
        while (isBust.not()) {
            val playerCards = game.hitFocusedPlayer().cards
            isBust = CardScoreCalculator().calculateScore(playerCards).isBust
        }
        Then("다음 플레이어로 순서가 넘어간다") {
            val turn = (game.currentTurn() as BlackJackGameTurn.HitOrStay)
            val playerName = turn.playerName
            playerName shouldBe playerNames[1]
        }
    }

    Given("플레이어가 stay 답변을 하면") {
        val playerNames = playerNames("test1", "test2")
        val game = BlackJackGame(
            shuffler = CardNotShuffler(),
            playerNames = playerNames,
        )
        game.distributeCardsToPlayers()
        game.stayFocusedPlayer()
        Then("다음 플레이어로 순서가 넘어간다") {
            val turn = game.currentTurn() as BlackJackGameTurn.HitOrStay
            turn.playerName shouldBe playerNames[1]
        }
    }

    Given("남아있는 플레이어가 없다면") {
        val playerNames = playerNames("test1", "test2")
        val game = BlackJackGame(
            shuffler = CardNotShuffler(),
            playerNames = playerNames,
        )
        game.distributeCardsToPlayers()
        game.stayFocusedPlayer()
        game.stayFocusedPlayer()
        Then("게임은 종료된다") {
            (game.currentTurn() is BlackJackGameTurn.Finished) shouldBe true
        }
    }
})
