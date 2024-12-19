package blackjack.domain

import blackjack.domain.StubDeck.Companion.DUMMY_SUIT
import blackjack.support.Fixtures.createHand
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class ReadyTest {
    @Test
    fun `카드 두 장 이하로 생성할 수 없다`() {
        assertSoftly {
            shouldThrow<IllegalArgumentException> { Ready(Hand()) }
            shouldThrow<IllegalArgumentException> { Ready(createHand(Rank.TWO)) }
        }
    }

    @Test
    fun `카드 한 정을 더 뽑아서 합이 21 미만이면 다시 READY 상태이다`() {
        val deck = StubDeck.from(Rank.FOUR)
        var state: State = Ready(createHand(Rank.TWO, Rank.THREE))

        state = state.drawFrom(deck)

        state.shouldBeInstanceOf<Ready>()
    }

    @Test
    fun `카드 한 정을 더 뽑아서 합이 21 미만이면 카드가 추가된다`() {
        val deck = StubDeck.from(Rank.FOUR)
        var state: State = Ready(createHand(Rank.TWO, Rank.THREE))

        state = state.drawFrom(deck)

        state.hand[2] shouldBe Card(DUMMY_SUIT, Rank.FOUR)
    }

    @Test
    fun `카드 한 장을 더 뽑아서 합이 21을 초과하면 BUSTED 상태가 된다`() {
        val deck = StubDeck.from(Rank.TEN)
        var state: State = Ready(createHand(Rank.TWO, Rank.KING))

        state = state.drawFrom(deck)

        state.shouldBeInstanceOf<Busted>()
    }

    @Test
    fun `카드 한 장을 더 뽑아서 합이 21이 되면 STAND 상태가 된다`() {
        val deck = StubDeck.from(Rank.SIX)
        var state: State = Ready(createHand(Rank.FIVE, Rank.TEN))

        state = state.drawFrom(deck)

        state.shouldBeInstanceOf<Stand>()
    }

    @Test
    fun `스탠드 할 경우 상태가 STAND 로 바뀐다`() {
        var state: State = Ready(createHand(Rank.FIVE, Rank.TEN))

        state = state.stand()

        state.shouldBeInstanceOf<Stand>()
    }
}
