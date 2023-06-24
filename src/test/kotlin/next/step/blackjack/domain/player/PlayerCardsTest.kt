package next.step.blackjack.domain.player

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.CardFace
import next.step.blackjack.domain.card.CardSymbol
import next.step.blackjack.util.CombinationUtils

class PlayerCardsTest : BehaviorSpec({

    Given("PlayerCards") {
        When("add하면") {
            val playerCards = PlayerCards.of(emptyList())
            val card = Card.of(CardFace.ACE, CardSymbol.CLUB)

            playerCards.add(card)

            Then("가지고 있는 카드를 추가함") {
                assertSoftly {
                    playerCards shouldBe PlayerCards.of(mutableListOf(card))
                    playerCards.size() shouldBe 1
                }
            }
        }

        When("카드 총 점수가 21점이 넘지 않으면") {
            val playerCards = PlayerCards.of(
                listOf(
                    Card.of(CardFace.KING, CardSymbol.CLUB),
                    Card.of(CardFace.KING, CardSymbol.HEART)
                )
            )

            Then("burst하지 않음") {
                playerCards.isBurst() shouldBe false
            }
            Then("블랙잭이 아님") {
                playerCards.isBlackJack() shouldBe false
            }
            Then("20점") {
                playerCards.point() shouldBe 20
            }
        }
        When("카드 총 개수가 2개이고, 총 점수가 21점이면") {
            val playerCards =
                PlayerCards.of(listOf(Card.of(CardFace.ACE, CardSymbol.CLUB), Card.of(CardFace.KING, CardSymbol.HEART)))

            Then("blackjack!") {
                playerCards.isBlackJack() shouldBe true
            }
        }
        When("카드를 총 점수가 21점이 넘으면") {
            val playerCards = PlayerCards.of(
                listOf(
                    Card.of(CardFace.KING, CardSymbol.CLUB),
                    Card.of(CardFace.KING, CardSymbol.HEART),
                    Card.of(CardFace.TWO, CardSymbol.DIAMOND)
                )
            )

            Then("burst함") {
                playerCards.isBurst() shouldBe true
            }
            Then("블랙잭이 아님") {
                playerCards.isBlackJack() shouldBe false
            }
            Then("22점") {
                playerCards.point() shouldBe 22
            }
        }
        When("Ace가 포함되어 최대점수가 21점보다 더 작으면") {
            val playerCards = PlayerCards.of(
                listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.NINE, CardSymbol.HEART),
                )
            )
            Then("최대점수를 점수로 제공함") {
                playerCards.point() shouldBe 20
            }
            Then("burst 하지 않음") {
                playerCards.isBurst() shouldBe false
            }
            Then("블랙잭이 아님") {
                playerCards.isBlackJack() shouldBe false
            }
        }
        When("Ace가 포함되어 최소점수가 21점보다 더 작고 최대점수가 21점보다 더 크면서 21점이 가능하지 않으면") {
            val playerCards = PlayerCards.of(
                listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.NINE, CardSymbol.HEART),
                    Card.of(CardFace.NINE, CardSymbol.CLUB),
                )
            )
            Then("가능한 점수는 20, 30, 40") {
                CombinationUtils.possiblePoints(playerCards.cards) shouldBe setOf(20, 30, 40)
            }
            Then("21점보다 작으면서 가장 큰 점수를 점수로 제공함") {
                playerCards.point() shouldBe 20
            }
            Then("burst 하지 않음") {
                playerCards.isBurst() shouldBe false
            }
            Then("블랙잭이 아님") {
                playerCards.isBlackJack() shouldBe false
            }
        }
        When("Ace가 포함되어 최소점수가 21점보다 더 크면") {
            val playerCards = PlayerCards.of(
                listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.NINE, CardSymbol.HEART),
                    Card.of(CardFace.NINE, CardSymbol.CLUB),
                    Card.of(CardFace.THREE, CardSymbol.HEART),
                )
            )
            Then("최소점수를 점수로 제공함") {
                playerCards.point() shouldBe 22
            }
            Then("burst 함") {
                playerCards.isBurst() shouldBe true
            }
            Then("블랙잭이 아님") {
                playerCards.isBlackJack() shouldBe false
            }
        }
        When("Ace가 포함되어 최대점수가 21점과 같으면") {
            val playerCards = PlayerCards.of(
                listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.NINE, CardSymbol.HEART),
                    Card.of(CardFace.ONE, CardSymbol.DIAMOND)
                )
            )
            Then("종료 점수를 점수로 제공함") {
                playerCards.point() shouldBe 21
            }
            Then("burst 안함") {
                playerCards.isBurst() shouldBe false
            }
            Then("종료 상태임") {
                playerCards.isFinished() shouldBe true
            }
        }
        When("Ace가 포함되어 최소점수가 21점과 같으면") {
            val playerCards = PlayerCards.of(
                listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.NINE, CardSymbol.HEART),
                    Card.of(CardFace.NINE, CardSymbol.CLUB),
                    Card.of(CardFace.TWO, CardSymbol.HEART),
                )
            )
            Then("블랙잭 점수를 점수로 제공함") {
                playerCards.point() shouldBe 21
            }
            Then("burst 안함") {
                playerCards.isBurst() shouldBe false
            }
            Then("종료 상태임") {
                playerCards.isFinished() shouldBe true
            }
            Then("카드 설명 제공") {
                playerCards.descs() shouldBe listOf("A클로버", "9하트", "9클로버", "2하트")
            }
        }
        When("Ace가 포함되어 21점이 가능하면") {
            val playerCards = PlayerCards.of(
                listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.NINE, CardSymbol.HEART),
                    Card.of(CardFace.NINE, CardSymbol.CLUB),
                    Card.of(CardFace.ONE, CardSymbol.HEART),
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                )
            )
            Then("블랙잭 점수를 점수로 제공함") {
                playerCards.point() shouldBe 21
            }
            Then("burst 안함") {
                playerCards.isBurst() shouldBe false
            }
            Then("종료 상태임") {
                playerCards.isFinished() shouldBe true
            }
        }
    }
})
