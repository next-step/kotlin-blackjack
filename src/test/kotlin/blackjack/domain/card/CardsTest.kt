package blackjack.domain.card

import blackjack.domain.Score
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe

class CardsTest : BehaviorSpec({

    Given("카드가 주어졌을 때 - 1") {
        val cards = Cards()
        val card = Card(number = CardNumber.ACE, pattern = CardPattern.CLOVER)

        When("카드를 한 장 추가하면") {
            cards.add(card)

            Then("카드가 추가된다.") {
                cards.values.size shouldBe 1
                cards.values shouldBe listOf(card)
            }
        }
    }

    Given("카드가 주어졌을 때 - 2") {
        val cards = Cards().apply {
            add(Card(number = CardNumber.TWO, pattern = CardPattern.CLOVER))
            add(Card(number = CardNumber.THREE, pattern = CardPattern.CLOVER))
        }

        When("카드의 점수를 계산하면") {
            val score = cards.calculateScore()

            Then("카드의 총 점수가 반환된다.") {
                score shouldBe Score(5)
            }
        }

        When("첫 번째 카드를 반환하면") {
            val result = cards.first()

            Then("첫 번째 카드가 반환된다.") {
                result shouldBeEqualToComparingFields Cards().apply {
                    add(Card(number = CardNumber.TWO, pattern = CardPattern.CLOVER))
                }
            }
        }

        When("버스트가 되지 않았다면") {
            val result = cards.isBust()

            Then("false를 반환한다.") {
                result.shouldBeFalse()
            }
        }

        When("블랙잭이 아니라면") {
            val result = cards.isBlackjack()

            Then("false를 반환한다.") {
                result.shouldBeFalse()
            }
        }
    }

    Given("카드가 주어졌을 때 - 3") {
        val cards = Cards().apply {
            add(Card(number = CardNumber.TEN, pattern = CardPattern.CLOVER))
            add(Card(number = CardNumber.KING, pattern = CardPattern.HEART))
            add(Card(number = CardNumber.JACK, pattern = CardPattern.HEART))
        }

        When("버스트가 되었다면") {
            val result = cards.isBust()

            Then("true를 반환한다.") {
                result.shouldBeTrue()
            }
        }
    }

    Given("카드가 주어졌을 때 - 4") {
        val cards = Cards().apply {
            add(Card(number = CardNumber.SIX, pattern = CardPattern.CLOVER))
            add(Card(number = CardNumber.SEVEN, pattern = CardPattern.HEART))
            add(Card(number = CardNumber.EIGHT, pattern = CardPattern.DIAMOND))
        }

        When("블랙잭이라면") {
            val result = cards.isBlackjack()

            Then("true를 반환한다.") {
                result.shouldBeTrue()
            }
        }
    }

    Given("카드에 에이스가 존재하면") {
        val cards = Cards().apply {
            add(Card(number = CardNumber.ACE, pattern = CardPattern.CLOVER))
            add(Card(number = CardNumber.TEN, pattern = CardPattern.HEART))
        }

        When("점수를 계산할 때") {
            val score = cards.calculateScore()

            Then("에이스의 점수 규칙에 따라 블랙잭에 가까운 값을 반환한다.") {
                score shouldBe Score(21)
            }
        }
    }
})
