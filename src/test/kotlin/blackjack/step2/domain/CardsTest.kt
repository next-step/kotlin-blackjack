package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class CardsTest : FunSpec({
    context("Cards 클래스 테스트") {
        test("all 테스트") {
            val card1 = Card(CardNumber.ACE, CardType.SPADE)
            val card2 = Card(CardNumber.KING, CardType.HEART)
            val cards = Cards.of(listOf(card1, card2))

            cards.all shouldContainExactly listOf(card1, card2)
        }

        test("add() 테스트") {
            val card1 = Card(CardNumber.ACE, CardType.SPADE)
            val card2 = Card(CardNumber.KING, CardType.HEART)
            val newCard = Card(CardNumber.TWO, CardType.DIAMOND)

            val cards = Cards.of(listOf(card1, card2))
            val updatedCards = cards.add(newCard)

            updatedCards.all shouldContainExactly listOf(card1, card2, newCard)
        }

        context("totalScore() 테스트") {
            context("ACE 카드를 갖고 있는 경우") {
                context("ACE 카드를 1장 갖고 있는 경우") {
                    test("ACE 카드가 11로 계산되는 경우 테스트") {
                        // given
                        val cards =
                            Cards.of(
                                listOf(
                                    Card(CardNumber.ACE, CardType.SPADE),
                                    Card(CardNumber.KING, CardType.HEART),
                                ),
                            )

                        // when
                        val result = cards.totalScore()

                        // then
                        result shouldBe 21 // ACE(11) + KING(10)
                    }

                    test("ACE 카드가 1로 계산되는 경우 테스트") {
                        // given
                        val cards =
                            Cards.of(
                                listOf(
                                    Card(CardNumber.TEN, CardType.HEART),
                                    Card(CardNumber.TEN, CardType.CLOVER),
                                    Card(CardNumber.ACE, CardType.SPADE),
                                ),
                            )

                        // when
                        val result = cards.totalScore()

                        // then
                        result shouldBe 21 // TEN(10) + TEN(10) + ACE(1)
                    }
                }
                context("ACE 카드를 2장 갖고 있는 경우") {
                    test("ACE 카드가 1장은 11로 계산되고, 1장은 1로 계산된다.") {
                        // given
                        val cards =
                            Cards.of(
                                listOf(
                                    Card(CardNumber.ACE, CardType.SPADE),
                                    Card(CardNumber.ACE, CardType.HEART),
                                ),
                            )

                        // when
                        val result = cards.totalScore()

                        // then
                        result shouldBe 12 // ACE(11) + ACE(1)
                    }
                }
                context("ACE 카드를 3장 갖고 있는 경우") {
                    test("ACE 카드가 1장은 11로 계산되고, 2장은 1로 계산된다.") {
                        // given
                        val cards =
                            Cards.of(
                                listOf(
                                    Card(CardNumber.ACE, CardType.SPADE),
                                    Card(CardNumber.ACE, CardType.HEART),
                                    Card(CardNumber.ACE, CardType.DIAMOND),
                                ),
                            )

                        // when
                        val result = cards.totalScore()

                        // then
                        result shouldBe 13 // ACE(11) + ACE(1) + ACE(1)
                    }
                }
            }
            context("ACE 카드를 갖고 있지 않은 경우") {
                test("숫자2와 숫자K를 갖고 있는 경우, 12점을 반환한다.") {
                    val card1 = Card(CardNumber.TWO, CardType.SPADE)
                    val card2 = Card(CardNumber.KING, CardType.HEART)
                    val cards = Cards.of(listOf(card1, card2))

                    cards.totalScore() shouldBe 12
                }
            }
        }
    }

    context("isInitialBlackjack() 테스트") {
        test("카드가 2장이며, 21점인 경우 true를 반환한다.") {
            val card1 = Card(CardNumber.TEN, CardType.SPADE)
            val card2 = Card(CardNumber.ACE, CardType.HEART)
            val cards = Cards.of(listOf(card1, card2))

            cards.isInitialBlackjack() shouldBe true
        }

        test("카드가 2장이 아닌 경우 false를 반환한다.") {
            val card1 = Card(CardNumber.TEN, CardType.SPADE)
            val card2 = Card(CardNumber.ACE, CardType.HEART)
            val card3 = Card(CardNumber.TWO, CardType.CLOVER)
            val cards = Cards.of(listOf(card1, card2, card3))

            cards.isInitialBlackjack() shouldBe false
        }

        test("카드가 2장이지만, 21점이 아닌 경우 false를 반환한다.") {
            val card1 = Card(CardNumber.TEN, CardType.SPADE)
            val card2 = Card(CardNumber.TEN, CardType.HEART)
            val cards = Cards.of(listOf(card1, card2))

            cards.isInitialBlackjack() shouldBe false
        }
    }
})
