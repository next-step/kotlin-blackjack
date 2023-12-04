package blackjack.domain.participant

import blackjack.domain.GameResult
import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class PlayerTest : BehaviorSpec({

    Given("카드가 주어졌을 때 - 1") {
        val player = createPlayer()
        val card = Card(number = CardNumber.ACE, pattern = CardPattern.CLOVER)

        When("플레이어가 한 장 받게 되면") {
            player.receiveCard(card)

            Then("플레이어의 카드는 한 장 증가한다.") {
                player.cards().values shouldContain card
            }
        }

        When("버스트가 아니라면") {
            val result = player.isBust()

            Then("false를 반환한다.") {
                result.shouldBeFalse()
            }
        }
    }

    Given("카드가 주어졌을 때 - 2") {
        val player = createPlayer()
        val card = Card(number = CardNumber.TEN, pattern = CardPattern.CLOVER)
        player.receiveCard(card)

        When("버스트라면") {
            val result = player.isBust()

            Then("true를 반환한다.") {
                result.shouldBeTrue()
            }
        }
    }

    Given("점수가 주어졌을 때") {
        val player = createPlayer()
        player.stay()

        When("점수를 비교하여 이겼다면") {
            val result = player.getResult(Score(19))

            Then("이긴 결과를 반환한다.") {
                result shouldBe GameResult.GameResultByPlayer(player.name(), GameResult.Result.WIN)
            }
        }

        When("점수를 비교하여 비겼다면") {
            val result = player.getResult(Score(20))

            Then("비긴 결과를 반환한다.") {
                result shouldBe GameResult.GameResultByPlayer(player.name(), GameResult.Result.DRAW)
            }
        }

        When("점수를 비교하여 졌다면") {
            val result = player.getResult(Score(21))

            Then("진 결과를 반환한다.") {
                result shouldBe GameResult.GameResultByPlayer(player.name(), GameResult.Result.LOSE)
            }
        }
    }
})

private fun createPlayer(): Player {
    val firstCard = Card(number = CardNumber.TEN, pattern = CardPattern.HEART)
    val secondCard = Card(number = CardNumber.TEN, pattern = CardPattern.DIAMOND)
    return Player(
        name = ParticipantName("플레이어1"),
        firstCard = firstCard,
        secondCard = secondCard,
    )
}
