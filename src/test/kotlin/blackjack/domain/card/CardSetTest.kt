package blackjack.domain.card

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardSetTest {

    @Test
    fun `카드 뭉치에 새로운 카드를 추가할 수 있다`() {
        val cardSet = CardSet.empty()
        val newCardSet = cardSet.addCard(Card.of(CardKind.DIAMOND, CardNumber.EIGHT))
        newCardSet.cards shouldBe listOf(
            Card.of(CardKind.DIAMOND, CardNumber.EIGHT)
        )
    }

    @Test
    fun `카드 뭉치에 중복된 카드가 있으면 예외가 발생한다`() {
        shouldThrow<IllegalArgumentException> {
            CardSet.of(
                Card.of(CardKind.DIAMOND, CardNumber.EIGHT),
                Card.of(CardKind.DIAMOND, CardNumber.EIGHT)
            )
        }
    }

    @Test
    fun `카드 뭉치에 중복된 카드를 추가하면 예외가 발생한다`() {
        val cardSet = CardSet.of(Card.of(CardKind.DIAMOND, CardNumber.EIGHT))
        shouldThrow<IllegalArgumentException> { cardSet.addCard(Card.of(CardKind.DIAMOND, CardNumber.EIGHT)) }
    }
}
