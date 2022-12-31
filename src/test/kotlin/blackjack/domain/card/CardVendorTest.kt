package blackjack.domain.card

import blackjack.domain.card.vendor.DefaultCardVendor
import blackjack.domain.player.CardHolder
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import org.junit.jupiter.api.assertThrows

class CardVendorTest : FunSpec({
    test("CardVendor가 정상적으로 1장씩 카드를 draw한다.") {
        // Given
        val allCardsSize = Cards.ALL.size
        val cardVendor = DefaultCardVendor()

        // When
        val drawCards = (1..allCardsSize).map { cardVendor.drawCard() }

        // Then
        drawCards shouldHaveSize allCardsSize
        drawCards shouldContainAll Cards.ALL
    }

    test("CardVendor가 모든 카드를 draw한 뒤, 자동으로 새카드를 꺼내서 draw한다.") {
        // Given
        val allCardsSize = Cards.ALL.size
        val cardVendor = DefaultCardVendor()

        repeat(allCardsSize) {
            cardVendor.drawCard()
        }

        // When
        val drawCards = (1..allCardsSize).map { cardVendor.drawCard() }

        // Then
        drawCards shouldHaveSize allCardsSize
        drawCards shouldContainAll Cards.ALL
    }

    test("CardVendor가 Player에게 게임 시작시 2장의 카드를 draw한다") {
        // Given
        val cardVendor = DefaultCardVendor()

        // When
        val playerFirstTwoCards = cardVendor.drawCards()

        // Then
        playerFirstTwoCards shouldHaveSize CardHolder.INIT_CARD_COUNT
        playerFirstTwoCards.toSet() shouldHaveSize CardHolder.INIT_CARD_COUNT
    }

    context("CardVendor::drawCards 0보다 같거나 큰 수일 경우, 정상 작동") {
        withData(
            nameFn = { "count: $it" },
            (0 to 20).toList()
        ) { count ->
            val cardVendor = DefaultCardVendor()

            // When
            val drawCards = cardVendor.drawCards(count)

            // Then
            drawCards shouldHaveSize count
            drawCards.toSet() shouldHaveSize count
        }
    }

    context("CardVendor::drawCards 메서드 파라미터가 0보다 작은 음수일 경우, IllegalArgumentException 발생") {
        withData(
            nameFn = { "count: $it" },
            (-1 downTo -20).toList()
        ) { negativeCount ->
            // Given
            val cardVendor = DefaultCardVendor()

            // Expected
            assertThrows<IllegalArgumentException> {
                cardVendor.drawCards(negativeCount)
            }
        }
    }
})
