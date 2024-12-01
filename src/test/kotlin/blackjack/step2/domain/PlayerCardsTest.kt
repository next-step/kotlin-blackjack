package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class PlayerCardsTest : FunSpec({
    context("PlayerCards 클래스 테스트") {
        test("PlayerCards를 생성할 수 있다.") {
            // Given
            val playerCard1 =
                PlayerCard.of(
                    "dongyeon",
                    listOf(Card(CardNumber.ACE, CardType.SPADE)),
                )
            val playerCard2 =
                PlayerCard.of(
                    "pobi",
                    listOf(Card(CardNumber.KING, CardType.HEART)),
                )
            val playerCards = listOf(playerCard1, playerCard2)

            // When
            val playerCardsInstance = PlayerCards.of(playerCards)

            // Then
            playerCardsInstance.cards shouldContainExactly playerCards
        }

        test("PlayerCards를 생성할 때 초기 리스트가 비어 있을 경우 빈 객체가 생성된다.") {
            // Given
            val emptyPlayerCards = emptyList<PlayerCard>()

            // When
            val playerCardsInstance = PlayerCards.of(emptyPlayerCards)

            // Then
            playerCardsInstance.cards shouldBe emptyList()
        }

        test("PlayerCards에 새로운 PlayerCard를 추가할 수 있다.") {
            // Given
            val playerCard1 =
                PlayerCard.of(
                    "dongyeon",
                    listOf(Card(CardNumber.ACE, CardType.SPADE)),
                )
            val playerCard2 =
                PlayerCard.of(
                    "pobi",
                    listOf(Card(CardNumber.KING, CardType.HEART)),
                )
            val playerCardsInstance = PlayerCards.of(listOf(playerCard1))

            // When
            val updatedPlayerCardsInstance = playerCardsInstance.add(playerCard2)

            // Then
            updatedPlayerCardsInstance.cards shouldContainExactly listOf(playerCard1, playerCard2)
        }

        test("PlayerCards의 원본 리스트는 불변이어야 한다.") {
            // Given
            val playerCard1 =
                PlayerCard.of(
                    "dongyeon",
                    listOf(Card(CardNumber.ACE, CardType.SPADE)),
                )
            val playerCard2 =
                PlayerCard.of(
                    "pobi",
                    listOf(Card(CardNumber.KING, CardType.HEART)),
                )
            val playerCardsInstance = PlayerCards.of(listOf(playerCard1))

            // When
            val updatedPlayerCardsInstance = playerCardsInstance.add(playerCard2)

            // Then
            playerCardsInstance.cards shouldContainExactly listOf(playerCard1) // 원본은 변하지 않음
            updatedPlayerCardsInstance.cards shouldContainExactly listOf(playerCard1, playerCard2) // 새로운 객체에 추가됨
        }

        test("PlayerCards의 cards 속성은 원본 리스트를 안전하게 캡슐화해야 한다.") {
            // Given
            val playerCard1 =
                PlayerCard.of(
                    "dongyeon",
                    listOf(Card(CardNumber.ACE, CardType.SPADE)),
                )
            val playerCardsInstance = PlayerCards.of(listOf(playerCard1))

            // When
            val retrievedCards = playerCardsInstance.cards

            // Then
            retrievedCards shouldContainExactly listOf(playerCard1)

            // 직접 접근하여 수정 시도
            val mutableCards = retrievedCards.toMutableList()
            mutableCards.add(
                PlayerCard.of("pobi", listOf(Card(CardNumber.KING, CardType.HEART))),
            )

            // PlayerCards의 원본 리스트는 변하지 않아야 함
            playerCardsInstance.cards shouldContainExactly listOf(playerCard1)
        }
    }
})
