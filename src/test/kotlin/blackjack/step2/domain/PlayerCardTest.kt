package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class PlayerCardTest : FunSpec({
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
                val playerCard = PlayerCard.of(playerName, initialCards)

                // When
                val allCards = playerCard.allCards

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
                val playerCard = PlayerCard.of(playerName, initialCards)
                val newCard = Card(CardNumber.QUEEN, CardType.DIAMOND)

                // When
                playerCard.pickCard(newCard)

                // Then
                playerCard.allCards shouldContainExactly initialCards + newCard
            }
        }
        context("calculateScore() 테스트") {
            context("ACE 카드를 갖고 있는 경우 경우") {
                test("ACE 카드를 1장 갖고 있는 경우, 21을 넘지 않으면서 21에 가까운 점수를 반환한다.") {
                    // Given
                    val playerName = "dongyeon"
                    val cards =
                        listOf(
                            // 1 또는 11
                            Card(CardNumber.ACE, CardType.SPADE),
                            // 10
                            Card(CardNumber.KING, CardType.HEART),
                        )
                    val playerCard = PlayerCard.of(playerName, cards)

                    // When
                    val score = playerCard.calculateScore()

                    // Then
                    score shouldBe 21 // ACE(11) + KING(10)
                }
                test("ACE 카드를 2장 갖고 있는 경우, 21을 넘지 않으면서 21에 가까운 점수를 반환한다.") {
                    // Given
                    val playerName = "dongyeon"
                    val cards =
                        listOf(
                            // 1 또는 11
                            Card(CardNumber.ACE, CardType.SPADE),
                            // 1 또는 11
                            Card(CardNumber.ACE, CardType.HEART),
                            // 9
                            Card(CardNumber.NINE, CardType.CLOVER),
                        )
                    val playerCard = PlayerCard.of(playerName, cards)

                    // When
                    val score = playerCard.calculateScore()

                    // Then
                    score shouldBe 21 // ACE(11) + ACE(1) + NINE(9)
                }
                test("ACE 카드를 3장 갖고 있는 경우, 21을 넘지 않으면서 21에 가까운 점수를 반환한다.") {
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
                    val playerCard = PlayerCard.of(playerName, cards)

                    // When
                    val score = playerCard.calculateScore()

                    // Then
                    score shouldBe 13 // 11 + 1 + 1
                }
            }
        }
    }
})
