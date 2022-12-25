package blackjack.domain.person

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class PlayerTest : BehaviorSpec({
    Given("플레이어가 주어지면") {
        val participant1 = Participant("길상현", 10000L, Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN))))
        When("카드를 추가할 수 ") {
            Then("있다.") {
                shouldNotThrowAny {
                    participant1.addCard(Card(CardShape.CLOVER, CardNumber.QUEEN))
                }
            }
        }

        val participant2 = Participant("길상현", 10000L, Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN))))
        When("점수를 계산할 수 ") {
            val score = participant2.getScore()
            Then("있다.") {
                score shouldBe 10
            }
        }

        When("카드의 개수를 계산할 수 ") {
            val size = participant2.getCardSize()
            Then("있다.") {
                size shouldBe 1
            }
        }

        val participant3 = Participant("길상현", 10000L, Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.QUEEN))))
        When("버스트인지 확인할 수 ") {
            val notBurst = participant2.isBurst()
            val burst = participant3.isBurst()
            Then("있다.") {
                notBurst shouldBe false
                burst shouldBe true
            }
        }

        val participant4 = Participant("길상현", 10000L, Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.ACE))))
        When("블랙잭인지 확인할 수 ") {
            val notBlackJack = participant2.isBlackJack()
            val blackJack = participant4.isBlackJack()
            Then("있다.") {
                notBlackJack shouldBe false
                blackJack shouldBe true
            }
        }
    }
})
