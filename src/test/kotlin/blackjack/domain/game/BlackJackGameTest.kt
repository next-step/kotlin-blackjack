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
})
