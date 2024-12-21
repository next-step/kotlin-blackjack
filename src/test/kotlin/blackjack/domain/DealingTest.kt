package blackjack.domain

import blackjack.support.Fixtures.createHand
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class DealingTest {
    @Test
    fun `카드 숫자가 두 장을 초과할 수 없다`() {
        assertSoftly {
            shouldThrow<IllegalArgumentException> { Dealing(createHand(Rank.TWO, Rank.THREE, Rank.FOUR)) }
        }
    }

    @Test
    fun `카드 한 장을 배급을 받으면 여전히 DEALING 상태이다`() {
        var state: State = Dealing()
        val deck = StubDeck.from(Rank.TWO)

        state = state.drawFrom(deck)

        state.shouldBeInstanceOf<Dealing>()
    }

    @Test
    fun `카드 한 장을 배급을 받으면, 그 카드를 첫 번째 카드로 들고 있다`() {
        var state: State = Dealing()
        val deck = StubDeck.from(Rank.TWO)

        state = state.drawFrom(deck)

        state.hand[0] shouldBe Card(StubDeck.DUMMY_SUIT, Rank.TWO)
    }

    @Test
    fun `카드 두 장을 배급 받으면, READY 상태로 넘어간다`() {
        val deck = StubDeck.from(Rank.TWO, Rank.THREE)
        var state = Dealing().drawFrom(deck)

        state = state.drawFrom(deck)

        state.shouldBeInstanceOf<Ready>()
    }

    @Test
    fun `카드 한 장을 더 배급을 받으면, 그 카드를 두 번째 카드로 들고 있다`() {
        val deck = StubDeck.from(Rank.TWO, Rank.THREE)
        var state: State = Dealing().drawFrom(deck)

        state = state.drawFrom(deck)

        state.hand[1] shouldBe Card(StubDeck.DUMMY_SUIT, Rank.THREE)
    }

    @Test
    fun `카드 두 장을 배급을 받아서 합이 21이 되면 BLACKJACK 이다`() {
        val deck = StubDeck.from(Rank.ACE, Rank.KING)
        var state: State = Dealing().drawFrom(deck)

        state = state.drawFrom(deck)

        state.shouldBeInstanceOf<Blackjack>()
    }
}
