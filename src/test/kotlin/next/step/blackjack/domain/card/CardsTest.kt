package next.step.blackjack.domain.card

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.game.GameResult
import org.junit.jupiter.api.assertThrows

class CardsTest : BehaviorSpec({

    Given("Cards") {
        When("add하면") {
            val cards = Cards.of(emptyList())
            val card = Card.of(CardFace.ACE, CardSymbol.CLUB)

            cards.add(card)

            Then("가지고 있는 카드를 추가함") {
                assertSoftly {
                    cards shouldBe Cards.of(mutableListOf(card))
                    cards.size() shouldBe 1
                }
            }
        }

        When("카드 총 점수가 21점이 넘지 않으면") {
            val cards = Cards.of(
                listOf(
                    Card.of(CardFace.KING, CardSymbol.CLUB),
                    Card.of(CardFace.KING, CardSymbol.HEART)
                )
            )

            Then("burst하지 않음") {
                cards.isBurst() shouldBe false
            }
            Then("블랙잭이 아님") {
                cards.isBlackjack() shouldBe false
            }
            Then("20점") {
                cards.point() shouldBe 20
            }
        }
        When("카드 총 개수가 2개이고, 총 점수가 21점이면") {
            val cards =
                Cards.of(listOf(Card.of(CardFace.ACE, CardSymbol.CLUB), Card.of(CardFace.KING, CardSymbol.HEART)))

            Then("blackjack!") {
                cards.isBlackjack() shouldBe true
            }
        }
        When("카드를 총 점수가 21점이 넘으면") {
            val cards = Cards.of(
                listOf(
                    Card.of(CardFace.KING, CardSymbol.CLUB),
                    Card.of(CardFace.KING, CardSymbol.HEART),
                    Card.of(CardFace.TWO, CardSymbol.DIAMOND)
                )
            )

            Then("burst함") {
                cards.isBurst() shouldBe true
            }
            Then("블랙잭이 아님") {
                cards.isBlackjack() shouldBe false
            }
            Then("22점") {
                cards.point() shouldBe 22
            }
        }
        When("Ace가 포함되어 최대점수가 21점보다 더 작으면") {
            val cards = Cards.of(
                listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.NINE, CardSymbol.HEART),
                )
            )
            Then("최대점수를 점수로 제공함") {
                cards.point() shouldBe 20
            }
            Then("burst 하지 않음") {
                cards.isBurst() shouldBe false
            }
            Then("블랙잭이 아님") {
                cards.isBlackjack() shouldBe false
            }
        }
        When("Ace가 포함되어 최소점수가 21점보다 더 작고 최대점수가 21점보다 더 크면서 21점이 가능하지 않으면") {
            val cards = Cards.of(
                listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.NINE, CardSymbol.HEART),
                    Card.of(CardFace.NINE, CardSymbol.CLUB),
                )
            )
            Then("21점보다 작으면서 가장 큰 점수를 점수로 제공함") {
                cards.point() shouldBe 20
            }
            Then("burst 하지 않음") {
                cards.isBurst() shouldBe false
            }
            Then("블랙잭이 아님") {
                cards.isBlackjack() shouldBe false
            }
        }
        When("Ace가 포함되어 최소점수가 21점보다 더 크면") {
            val cards = Cards.of(
                listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.NINE, CardSymbol.HEART),
                    Card.of(CardFace.NINE, CardSymbol.CLUB),
                    Card.of(CardFace.THREE, CardSymbol.HEART),
                )
            )
            Then("최소점수를 점수로 제공함") {
                cards.point() shouldBe 22
            }
            Then("burst 함") {
                cards.isBurst() shouldBe true
            }
            Then("블랙잭이 아님") {
                cards.isBlackjack() shouldBe false
            }
        }
        When("Ace가 포함되어 최대점수가 21점과 같으면") {
            val cards = Cards.of(
                listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.NINE, CardSymbol.HEART),
                    Card.of(CardFace.ONE, CardSymbol.DIAMOND)
                )
            )
            Then("종료 점수를 점수로 제공함") {
                cards.point() shouldBe 21
            }
            Then("burst 안함") {
                cards.isBurst() shouldBe false
            }
            Then("종료 상태임") {
                cards.isHit() shouldBe true
            }
        }
        When("Ace가 포함되어 최소점수가 21점과 같으면") {
            val cards = Cards.of(
                listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.NINE, CardSymbol.HEART),
                    Card.of(CardFace.NINE, CardSymbol.CLUB),
                    Card.of(CardFace.TWO, CardSymbol.HEART),
                )
            )
            Then("블랙잭 점수를 점수로 제공함") {
                cards.point() shouldBe 21
            }
            Then("burst 안함") {
                cards.isBurst() shouldBe false
            }
            Then("종료 상태임") {
                cards.isHit() shouldBe true
            }
            Then("카드 설명 제공") {
                cards.descs() shouldBe listOf("A클로버", "9하트", "9클로버", "2하트")
            }
        }
        When("Ace가 포함되어 21점이 가능하면") {
            val cards = Cards.of(
                listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.NINE, CardSymbol.HEART),
                    Card.of(CardFace.NINE, CardSymbol.CLUB),
                    Card.of(CardFace.ONE, CardSymbol.HEART),
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                )
            )
            Then("블랙잭 점수를 점수로 제공함") {
                cards.point() shouldBe 21
            }
            Then("burst 안함") {
                cards.isBurst() shouldBe false
            }
            Then("종료 상태임") {
                cards.isHit() shouldBe true
            }
        }
        When("첫번째 desc 찾는데 카드가 있으면") {
            val cards = Cards.of(
                listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.NINE, CardSymbol.HEART),
                    Card.of(CardFace.NINE, CardSymbol.CLUB),
                    Card.of(CardFace.ONE, CardSymbol.HEART),
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                )
            )
            Then("첫번째 카드 desc 제공") {
                cards.descFirst() shouldBe "A클로버"
            }
        }
        When("첫번째 desc 찾는데 카드가 없으면") {
            val cards = Cards.of(emptyList())
            Then("예외 발생") {
                assertThrows<IllegalArgumentException> { cards.descFirst() }
            }
        }
    }

    Given("Cards fight") {
        val cards = Cards.of(
            listOf(
                Card.of(CardFace.ACE, CardSymbol.CLUB),
                Card.of(CardFace.NINE, CardSymbol.HEART),
            )
        )
        When("점수가 더 큰 Cards와 fight하면") {
            val otherCards = Cards.of(
                listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.TEN, CardSymbol.HEART),
                )
            )
            Then("짐") {
                cards.fight(otherCards) shouldBe GameResult.LOSE
            }
        }
        When("점수가 더 작은 Cards와 fight하면") {
            val otherCards = Cards.of(
                listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.EIGHT, CardSymbol.HEART),
                )
            )
            Then("이김") {
                cards.fight(otherCards) shouldBe GameResult.WIN
            }
        }
        When("점수가 더 큰 Cards와 fight하면") {
            val otherCards = Cards.of(
                listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.NINE, CardSymbol.HEART),
                )
            )
            Then("비김") {
                cards.fight(otherCards) shouldBe GameResult.TIE
            }
        }
    }
})
