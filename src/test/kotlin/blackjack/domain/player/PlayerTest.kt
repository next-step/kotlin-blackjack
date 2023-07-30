package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.CardRank
import blackjack.domain.Challenger
import blackjack.domain.SPADES_TEN
import blackjack.domain.SPADES_TWO
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

internal class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = ["홍길동", "한맹희"])
    internal fun `플레이어는 이름을 가진다`(value: String) {
        val sut = Challenger(
            name = value,
        )
        sut.name shouldBe value
    }

    @Test
    internal fun `플레이어는 카드를 받고 점수를 계산할 수 있다`() {
        val sut = Challenger("A")
        sut.initializeHands(SPADES_TWO, SPADES_TEN)
        sut.score().toInt() shouldBe 12
        sut.receive(SPADES_TWO)
        sut.score().toInt() shouldBe 14
    }

    @Test
    internal fun `플레이어는 카드 점수 합계가 21점을 넘으면 버스트 된다`() {
        val sut = Challenger("A")
        sut.initializeHands(SPADES_TWO, SPADES_TEN)
        sut.isBurst() shouldBe false
        sut.receive(SPADES_TEN)
        sut.isBurst() shouldBe true
    }

    @Test
    internal fun `플레이어가 히트할 수 없을때 카드를 받으면 예외가 발생한다`() {
        val sut = Challenger("A")
        sut.initializeHands(SPADES_TWO, SPADES_TEN)
        sut.receive(SPADES_TEN)
        assertThrows<IllegalStateException> { sut.receive(SPADES_TEN) }
    }

    @ParameterizedTest
    @CsvSource(
        "ACE, TEN, true",
        "ACE, NINE, false",
        "TWO, THREE, false",
        "JACK, ACE, true",
    )
    internal fun `플레이어가 최초로 카드를 두장을 받았을때 점수가 21이면 블랙잭이다`(
        firstCard: String,
        secondCard: String,
        expectedBlackjack: Boolean,
    ) {
        val sut = Challenger("A")

        sut.initializeHands(
            Card.of(CardRank.valueOf(firstCard)),
            Card.of(CardRank.valueOf(secondCard)),
        )
        sut.isBlackjack() shouldBe expectedBlackjack
    }

    @Test
    internal fun `시작 상태가 아닐때 핸드를 다시 구성하면 예외가 발생한다`() {
        val sut = Challenger("A")
        sut.initializeHands(SPADES_TEN, SPADES_TEN)
        assertThrows<IllegalStateException> { sut.initializeHands(SPADES_TEN, SPADES_TEN) }
    }
}
