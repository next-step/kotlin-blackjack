package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.CardCharacter
import blackjack.domain.card.CardShape
import blackjack.domain.card.Hands
import io.kotest.matchers.types.shouldBeTypeOf
import org.junit.jupiter.api.Test

class HitTest {
    @Test
    fun `Hit 상태에서 한장을 더 받으면 Hit 상태다`() {
        val hit = Hit(Hands(Card(CardCharacter.TWO, CardShape.CLUB), Card(CardCharacter.TEN, CardShape.HEART)))
        hit.draw(Card(CardCharacter.THREE, CardShape.CLUB)).shouldBeTypeOf<Hit>()
    }

    @Test
    fun `Hit 상태에서 한장을 더 받을 때 21점을 초과하면 Bust 상태다`() {
        val hit = Hit(Hands(Card(CardCharacter.KING, CardShape.CLUB), Card(CardCharacter.TEN, CardShape.HEART)))
        hit.draw(Card(CardCharacter.THREE, CardShape.CLUB)).shouldBeTypeOf<Bust>()
    }

    @Test
    fun `Hit 상태에서 카드를 더 받지 않는다면 Stay 상태다`() {
        val hit = Hit(Hands(Card(CardCharacter.KING, CardShape.CLUB), Card(CardCharacter.TEN, CardShape.HEART)))
        hit.stay().shouldBeTypeOf<Stay>()
    }
}
