package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class DealerTest : FunSpec({
    context("Dealer 클래스 테스트") {
        test("플레이어들에게 초기 두 장의 카드를 분배한다.") {
            // Given
            val cardPicker = DefaultCardPicker()
            val dealer = Dealer(cardPicker)
            val players = Players.of(listOf("dongyeon", "pobi"))

            // When
            val playerCards = dealer.dealFirstCards(players)

            // Then
            playerCards.cards shouldHaveSize 2
            playerCards.cards[0].playerName shouldBe "dongyeon"
            playerCards.cards[1].playerName shouldBe "pobi"
            playerCards.cards[0].allCards shouldHaveSize 2
            playerCards.cards[1].allCards shouldHaveSize 2
        }

        test("플레이어에게 새로운 카드를 분배한다.") {
            // Given
            val cardPicker = DefaultCardPicker()
            val dealer = Dealer(cardPicker)

            val initialCards = cardPicker.pick(count = 2)
            val playerCard = PlayerCard.of("dongyeon", initialCards)

            // When
            val updatedPlayerCard = dealer.dealCard(playerCard)

            // Then
            updatedPlayerCard.allCards shouldHaveSize 3
        }
    }
})
