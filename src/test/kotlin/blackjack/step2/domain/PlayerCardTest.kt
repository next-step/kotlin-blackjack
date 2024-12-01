package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class PlayerCardTest : FunSpec({
    context("PlayerCard 클래스 테스트") {
        test("PlayerCard를 생성할 수 있다.") {
            // Given
            val playerName = "dongyeon"
            val initialCards =
                listOf(
                    Card(CardNumber.ACE, CardType.SPADE),
                    Card(CardNumber.KING, CardType.HEART),
                )

            // When
            val playerCard = PlayerCard.of(playerName, initialCards)

            // Then
            playerCard.playerName shouldBe playerName
            playerCard.allCards shouldContainExactly initialCards
        }

        test("PlayerCard를 생성할 때 초기 카드가 없는 경우 빈 리스트로 생성된다.") {
            // Given
            val playerName = "dongyeon"

            // When
            val playerCard = PlayerCard.of(playerName)

            // Then
            playerCard.playerName shouldBe playerName
            playerCard.allCards shouldBe emptyList()
        }

        test("PlayerCard에 새로운 카드를 추가할 수 있다.") {
            // Given
            val playerName = "dongyeon"
            val initialCards =
                listOf(
                    Card(CardNumber.QUEEN, CardType.DIAMOND),
                )
            val newCard = Card(CardNumber.TEN, CardType.CLOVER)
            val playerCard = PlayerCard.of(playerName, initialCards)

            // When
            val updatedPlayerCard = playerCard.addCard(newCard)

            // Then
            updatedPlayerCard.allCards shouldContainExactly initialCards + newCard
        }

        test("PlayerCard의 점수를 계산할 수 있다.") {
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

        test("PlayerCard의 점수가 21을 초과하지 않는 최대 값을 반환한다.") {
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

        test("PlayerCard의 모든 점수가 21을 초과하면 최소 값을 반환한다.") {
            // Given
            val playerName = "dongyeon"
            val cards =
                listOf(
                    // 10
                    Card(CardNumber.KING, CardType.SPADE),
                    // 10
                    Card(CardNumber.KING, CardType.HEART),
                    // 10
                    Card(CardNumber.QUEEN, CardType.DIAMOND),
                )
            val playerCard = PlayerCard.of(playerName, cards)

            // When
            val score = playerCard.calculateScore()

            // Then
            score shouldBe 30 // 10 + 10 + 10
        }
    }
})
