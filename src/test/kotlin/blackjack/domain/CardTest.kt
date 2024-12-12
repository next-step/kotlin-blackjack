package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CardTest : BehaviorSpec({
    Given("`CardRank`, `CardSuit`를 가진다") {
        When("Card를 생성하면") {
            Then("CardRank와 CardSuit를 가진다") {
                val actual = Card(CardRank.ACE, Suit.SPADE)

                actual.rank shouldBe CardRank.ACE
                actual.suit shouldBe Suit.SPADE
            }
        }
    }

    Given("`Score` 알 수 있다") {
        listOf(
            Card(CardRank.ACE, Suit.SPADE) to Score(11),
            Card(CardRank.JACK, Suit.SPADE) to Score(10),
            Card(CardRank.NINE, Suit.SPADE) to Score(9),
        ).forEach { (card, expectedScore) ->
            When("${card.rank}일 때") {
                Then("${expectedScore}로 계산할 수 있다") {
                    card.score(Score.ZERO) shouldBe expectedScore
                }
            }
        }
    }

    Given("이름을 알 수 있다") {
        listOf(
            Card(CardRank.ACE, Suit.SPADE) to CardRank.ACE,
            Card(CardRank.ACE, Suit.HEART) to CardRank.ACE,
            Card(CardRank.JACK, Suit.DIAMOND) to CardRank.JACK,
            Card(CardRank.NINE, Suit.CLUB) to CardRank.NINE,
        ).forEach { (card, expected) ->
            When("CardSuit가 ${card.suit}일 때") {
                Then("rank는 $expected 이다") {
                    card.rank shouldBe expected
                }
            }
        }
    }
})
