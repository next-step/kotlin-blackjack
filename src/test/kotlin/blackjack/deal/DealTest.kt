package blackjack.deal

import blackjack.domain.Deal
import blackjack.domain.enums.Card
import blackjack.domain.enums.CardSymbol
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.collections.shouldBeUnique
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldInclude
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DealTest {
    @Test
    fun `요청한 카드 수만큼 반환된다`() {
        val cards = Deal.giveCards(5)
        cards.size shouldBe 5
    }

    @Test
    fun `카드가 중복되지 않는다`() {
        val cards = Deal.giveCards(10)

        cards.shouldBeUnique()
    }

    @Test
    fun `카드는 가능한 조합에서만 반환된다`() {

        val cards = Deal.giveCards(3)

        cards.shouldNotBeEmpty()

        cards.forEach { (cardMap, _) ->
            val (symbol, card) = cardMap.entries.first()
            symbol shouldBeIn CardSymbol.entries
            card shouldBeIn Card.entries
        }
    }

    @Test
    fun `카드 요청 수가 덱의 총 카드 수를 초과하면 실패`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                Deal.giveCards(100)
            }
        exception.message shouldInclude "Requested cards exceed the available deck size. Remaining:"
    }
}
