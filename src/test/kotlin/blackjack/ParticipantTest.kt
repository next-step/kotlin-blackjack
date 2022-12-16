package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import blackjack.domain.enums.WinOrLose
import blackjack.domain.person.Dealer
import blackjack.domain.person.Participant
import blackjack.domain.strategy.SequentialCardPickStrategy
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class ParticipantTest : BehaviorSpec({
    Given("특정 사람이 ") {
        val participant1 = Participant("길상현")
        When("카드를 추가하면 ") {
            participant1.addCard(Card(CardShape.CLOVER, CardNumber.QUEEN))
            Then("정상적으로 카드를 추가한다.") {
                participant1.name shouldBe "길상현"
                participant1.cards.cards.first().cardShape shouldBe CardShape.CLOVER
                participant1.cards.cards.first().cardNumber shouldBe CardNumber.QUEEN
            }
        }

        val participant2 = Participant("길상현", Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN))))
        When("점수를 계산하면 ") {
            val score = participant2.getScore()
            Then("정상적으로 점수를 계산한다.") {
                score shouldBe 10
            }
        }

        val participant3 = Participant("길상현", Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.NUM_9))))
        When("승패를 계산할 때, 딜러보다 21에 가까우면 ") {
            val dealer = Dealer("딜러", Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.NUM_8))), SequentialCardPickStrategy())
            val gameResult = participant3.getGameResult(dealer)
            Then("이긴다.") {
                dealer.getScore() shouldBe 18
                participant3.getScore() shouldBe 19
                gameResult shouldBe WinOrLose.WIN
            }
        }

        When("승패를 계산할 때, 딜러가 21을 초과하면 ") {
            val dealer = Dealer("딜러", Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.QUEEN))), SequentialCardPickStrategy())
            val gameResult = participant3.getGameResult(dealer)
            Then("이긴다.") {
                dealer.getScore() shouldBe 30
                participant3.getScore() shouldBe 19
                gameResult shouldBe WinOrLose.WIN
            }
        }

        When("승패를 계산할 때, 딜러가 21에 더 가깝다면 ") {
            val dealer = Dealer("딜러", Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.QUEEN))), SequentialCardPickStrategy())
            val gameResult = participant3.getGameResult(dealer)
            Then("진다.") {
                dealer.getScore() shouldBe 20
                participant3.getScore() shouldBe 19
                gameResult shouldBe WinOrLose.LOSE
            }
        }
    }
})
