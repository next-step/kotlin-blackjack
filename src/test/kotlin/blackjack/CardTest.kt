package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CardTest : BehaviorSpec({

    Given("`CardRank`, `CardSuit`를 가진다") {
        When("Card를 생성하면") {
            Then("CardRank와 CardSuit를 가진다") {
                val actual = Card("A", Suit.SPADE)

                actual.rank shouldBe CardRank.Ace
                actual.suit shouldBe Suit.SPADE
            }
        }
    }

    Given("`Score` 알 수 있다") {
        listOf(
            Card("A", Suit.SPADE) to 11,
            Card("J", Suit.SPADE) to 10,
            Card("9", Suit.SPADE) to 9,
        ).forEach { (card, expectedScore) ->
            When("${card.rank}일 때") {
                Then("${expectedScore}로 계산할 수 있다") {
                    card.score(0) shouldBe expectedScore
                }
            }
        }
    }

    Given("이름을 알 수 있다") {
        listOf(
            Card("A", Suit.SPADE) to "A스페이드",
            Card("A", Suit.HEART) to "A하트",
            Card("J", Suit.DIAMOND) to "J다이아몬드",
            Card("9", Suit.CLUB) to "9클로버",
        ).forEach { (card, expected) ->
            When("CardSuit가 ${card.suit}일 때") {
                Then("$expected 로 표시된다") {
                    card.name shouldBe expected
                }
            }
        }
    }
})
