package next.step.blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerTest : BehaviorSpec({

    Given("Player") {
        When("hit하면") {
            val player = Player.of("dj", PlayerCards.of(emptyList()))
            val card = Card.of(CardFace.ACE, CardSymbol.CLUB)

            player.hit(card)

            Then("가지고 있는 카드를 추가함") {
                player shouldBe Player.of("dj", PlayerCards.of(mutableListOf(card)))
            }
        }

        When("가지고 있는 카드 총 점수가 21점이 넘지 않으면") {
            val player = Player.of(
                "dj",
                PlayerCards.of(
                    listOf(
                        Card.of(CardFace.KING, CardSymbol.CLUB),
                        Card.of(CardFace.KING, CardSymbol.HEART)
                    )
                )
            )

            Then("burst하지 않음") {
                player.isBurst() shouldBe false
            }

            Then("블랙잭이 아님") {
                player.isBlackJack() shouldBe false
            }
        }
        When("가지고 있는 카드 총 점수가 21점이면") {
            val player = Player.of(
                "dj",
                PlayerCards.of(listOf(Card.of(CardFace.ACE, CardSymbol.CLUB), Card.of(CardFace.KING, CardSymbol.HEART)))
            )

            Then("blackjack!") {
                player.isBlackJack() shouldBe true
            }
        }
        When("카드를 더 받아서 총 점수가 21점이 넘으면") {
            val player = Player.of(
                "dj",
                PlayerCards.of(
                    listOf(
                        Card.of(CardFace.KING, CardSymbol.CLUB),
                        Card.of(CardFace.KING, CardSymbol.HEART)
                    )
                )
            )

            player.hit(Card.of(CardFace.TWO, CardSymbol.DIAMOND))

            Then("burst함") {
                player.isBurst() shouldBe true
            }
            Then("블랙잭이 아님") {
                player.isBlackJack() shouldBe false
            }
        }

    }

})
