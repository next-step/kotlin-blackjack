package blackjack.deal

import blackjack.enums.Card
import blackjack.enums.CardSymbol
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.maps.shouldNotBeEmpty
import org.junit.jupiter.api.Test

class DealTest {
    @Test
    fun `랜덤한 문양의 카드를 지급`() {
        val card = Deal.giveCard()

        card.shouldNotBeEmpty()
        val (symbol, cardValue) = card.entries.first()

        symbol shouldBeIn CardSymbol.entries

        cardValue shouldBeIn Card.entries
    }

    @Test
    fun `다양한 문양과 카드 값 반환되는지 반복`() {
        val results = mutableSetOf<Map<CardSymbol, Card>>()
        repeat(100) { results.add(Deal.giveCard()) }
        results.size shouldBeGreaterThan 10
    }
}
