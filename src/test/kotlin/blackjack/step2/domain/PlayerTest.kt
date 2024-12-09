package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class PlayerTest : FunSpec({
    context("PlayerCard 클래스 테스트") {
        context("allCards 테스트") {
            test("PlayerCard의 모든 카드를 확인할 수 있다.") {
                // Given
                val playerName = "dongyeon"
                val initialCards =
                    listOf(
                        Card(CardNumber.ACE, CardType.SPADE),
                        Card(CardNumber.KING, CardType.HEART),
                    )
                val player = Player.of(playerName, initialCards)

                // When
                val allCards = player.cards.all

                // Then
                allCards shouldContainExactly initialCards
            }
        }
        context("pickCard() 테스트") {
            test("PlayerCard에 카드를 추가로 뽑을 수 있다.") {
                // Given
                val playerName = "dongyeon"
                val initialCards =
                    listOf(
                        Card(CardNumber.ACE, CardType.SPADE),
                        Card(CardNumber.KING, CardType.HEART),
                    )
                val player = Player.of(playerName, initialCards)
                val newCard = Card(CardNumber.QUEEN, CardType.DIAMOND)

                // When
                val updatedPlayerCard = player.pickCard(newCard)

                // Then
                updatedPlayerCard.cards.all shouldContainExactly initialCards.plus(newCard)
            }
        }
        context("calculateScore() 테스트") {
            context("ACE 카드를 갖고 있는 경우 경우") {
                context("ACE 카드를 1장 갖고 있는 경우") {
                    test("ACE 카드가 11로 계산되는 경우, 21을 넘지 않으면서 21에 가까운 점수를 반환한다.") {
                        // Given
                        val playerName = "dongyeon"
                        val cards =
                            listOf(
                                // 1 또는 11
                                Card(CardNumber.ACE, CardType.SPADE),
                                // 10
                                Card(CardNumber.KING, CardType.HEART),
                            )
                        val player = Player.of(playerName, cards)

                        // When
                        val score = player.calculateScore()

                        // Then
                        score shouldBe 21 // ACE(11) + KING(10)
                    }
                    test("ACE 카드가 1로 계산되는 경우, 21을 넘지 않으면서 21에 가까운 점수를 반환한다.") {
                        // Given
                        val playerName = "dongyeon"
                        val cards =
                            listOf(
                                // 10
                                Card(CardNumber.KING, CardType.HEART),
                                // 2
                                Card(CardNumber.TWO, CardType.CLOVER),
                                // 1 또는 11
                                Card(CardNumber.ACE, CardType.SPADE),
                            )
                        val player = Player.of(playerName, cards)

                        // When
                        val score = player.calculateScore()

                        // Then
                        score shouldBe 13 // KING(10) + TWO(2) + ACE(1)
                    }
                }

                context("ACE 카드를 2장 갖고 있는 경우") {
                    test("ACE 카드가 1장은 11로 계산되고, 1장은 1로 계산된다.") {
                        // Given
                        val playerName = "dongyeon"
                        val cards =
                            listOf(
                                // 1 또는 11
                                Card(CardNumber.ACE, CardType.SPADE),
                                // 1 또는 11
                                Card(CardNumber.ACE, CardType.HEART),
                            )
                        val player = Player.of(playerName, cards)

                        // When
                        val score = player.calculateScore()

                        // Then
                        score shouldBe 12 // ACE(11) + ACE(1)
                    }
                }
                context("ACE 카드를 3장 갖고 있는 경우") {
                    test("ACE 카드가 1장은 11로 계산되고, 2장은 1로 계산된다.") {
                        // Given
                        val playerName = "dongyeon"
                        val cards =
                            listOf(
                                // 1 또는 11
                                Card(CardNumber.ACE, CardType.SPADE),
                                // 1 또는 11
                                Card(CardNumber.ACE, CardType.HEART),
                                // 1 또는 11
                                Card(CardNumber.ACE, CardType.DIAMOND),
                            )
                        val player = Player.of(playerName, cards)

                        // When
                        val score = player.calculateScore()

                        // Then
                        score shouldBe 13 // ACE(11) + ACE(1) + ACE(1)
                    }
                }
            }
            context("ACE 카드를 갖고 있지 않은 경우") {
                test("ACE 카드가 없는 경우, 카드의 숫자 합을 반환한다.") {
                    // Given
                    val playerName = "dongyeon"
                    val cards =
                        listOf(
                            // 10
                            Card(CardNumber.KING, CardType.HEART),
                            // 2
                            Card(CardNumber.TWO, CardType.CLOVER),
                        )
                    val player = Player.of(playerName, cards)

                    // When
                    val score = player.calculateScore()

                    // Then
                    score shouldBe 12 // KING(10) + TWO(2)
                }
            }
        }
    }
})
