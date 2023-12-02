package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.CardCharacter
import blackjack.domain.card.CardShape
import io.kotest.matchers.types.shouldBeTypeOf
import org.junit.jupiter.api.Test

class FirstTest {
    @Test
    fun `first 상태에서 1장의 카드를 받으면 여전히 First 상태다`() {
        val state = First()
        val actual = state.draw(Card(CardCharacter.FIVE, CardShape.CLUB))
        actual.shouldBeTypeOf<First>()
    }

    @Test
    fun `first 상태에서 2장의 카드를 받으면 Hit 상태다`() {
        val state = First()
        val actual = state.draw(Card(CardCharacter.FIVE, CardShape.CLUB))
            .draw(Card(CardCharacter.SIX, CardShape.CLUB))
        actual.shouldBeTypeOf<Hit>()
    }

    @Test
    fun `first 상태에서 2장의 카드의 합이 21인 카드를 받으면 Blackjack 상태다`() {
        val state = First()
        val actual = state.draw(Card(CardCharacter.ACE, CardShape.CLUB))
            .draw(Card(CardCharacter.TEN, CardShape.CLUB))
        actual.shouldBeTypeOf<Blackjack>()
    }

    @Test
    fun `first 상태일 때 Ace를 두 장 받으면 Hit 상태다`() {
        val state = First()
        val actual = state.draw(Card(CardCharacter.ACE, CardShape.CLUB))
            .draw(Card(CardCharacter.ACE, CardShape.HEART))
        actual.shouldBeTypeOf<Hit>()
    }
}
