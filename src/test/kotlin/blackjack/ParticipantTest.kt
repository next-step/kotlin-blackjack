package blackjack

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardShape
import blackjack.domain.Cards
import blackjack.domain.Participant
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
    }
})
