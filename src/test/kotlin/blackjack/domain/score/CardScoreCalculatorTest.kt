package blackjack.domain.score

import blackjack.domain.card.heartAce
import blackjack.domain.card.heartJack
import blackjack.domain.card.heartTwo
import blackjack.domain.card.spadeAce
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class CardScoreCalculatorTest : BehaviorSpec({

    Given("카드 목록에 에이스가 존재할 때") {
        When("에이스를 11로 선택하면 카드의 합이 21을 초과할 경우") {
            forAll(
                row(
                    listOf(
                        heartAce(),
                        heartTwo(),
                        heartJack(),
                    ),
                    13, // A(1) + Two(2) + J(10) = 13 (A를 11로 계산하면 24로, 21 초과)
                ),
                row(
                    listOf(
                        heartAce(),
                        spadeAce(),
                        heartJack(),
                    ),
                    12, // A하트(1) + A스페이드(1) + J(10) = 12 (A를 한장이라도 11로 계산하면 21 초과)
                )
            ) { cards, expected ->
                Then("에이스는 1로 계산한다") {
                    CardScoreCalculator.calculateScore(cards).value shouldBe expected
                }
            }
        }

        When("에이스를 11로 선택해도 카드의 합이 21을 초과하지 않는 경우") {
            forAll(
                row(
                    listOf(
                        heartAce(),
                    ),
                    11, // A(11)
                ),
                row(
                    listOf(
                        heartAce(),
                        heartJack(),
                    ),
                    21, // A(11) + J(10) = 21
                ),
                row(
                    listOf(
                        heartAce(),
                        spadeAce(),
                        heartTwo(),
                    ),
                    14, // A(11) + A(1) + Two(2) = 14 (A 한장은 11로 계산해도 21을 초과하지 않음)
                ),
            ) { cards, expected ->
                Then("에이스는 11로 계산한다") {
                    CardScoreCalculator.calculateScore(cards).value shouldBe expected
                }
            }
        }
    }
})
