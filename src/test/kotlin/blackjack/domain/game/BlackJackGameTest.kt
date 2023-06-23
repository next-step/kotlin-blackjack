package blackjack.domain.game

import blackjack.domain.player.playerNames
import blackjack.domain.shuffle.CardNotShuffler
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : BehaviorSpec({

    Given("카드를 분배하면") {
        val game = BlackJackGame(
            shuffler = CardNotShuffler(),
            playerNames = playerNames("test1", "test2"),
        )
        val result = game.distributeCardsToPlayers()

        Then("플레이어들은 2장씩 카드를 받는다") {
            result.countOfCardDistribution shouldBe 2
        }
    }

    Given("카드 배분을 했음에도 다시 카드 배분을 하면") {
        val game = BlackJackGame(
            shuffler = CardNotShuffler(),
            playerNames = playerNames("test1", "test2"),
        )
        Then("RuntimeException 예외 처리를 한다") {
            shouldThrow<RuntimeException> {
                game.distributeCardsToPlayers()
                game.distributeCardsToPlayers()
            }
        }
    }

    Given("카드 분배 이후 다음 턴으로 넘어가면") {
        val playerNames = playerNames("test1", "test2")
        val game = BlackJackGame(
            shuffler = CardNotShuffler(),
            playerNames = playerNames,
        ).apply {
            distributeCardsToPlayers()
        }
        Then("대기중인 첫 플레이어의 hit 대답을 기다린다") {
            val turn = game.nextTurn()
            turn shouldBe BlackJackGameTurn.HitAnswerWait(playerNames[0])
        }
    }

    Given("플레이어가 hit 답변을 하면") {
        val playerNames = playerNames("test1")
        val game = BlackJackGame(
            shuffler = CardNotShuffler(),
            playerNames = playerNames,
        )
        val playerCardsSize = game.distributeCardsToPlayers()
            .playerCardDeckCaptures[0]
            .cards
            .size
        Then("플레이어는 한장의 카드를 받는다") {
            val hitResult = game.hitFocusedPlayer()
            hitResult.cards.size shouldBe playerCardsSize + 1
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
            val turn = game.nextTurn() as BlackJackGameTurn.HitAnswerWait
            turn.playerName shouldBe playerNames[1]
        }
    }
})
