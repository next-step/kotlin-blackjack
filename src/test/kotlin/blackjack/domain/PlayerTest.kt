package blackjack.domain

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
        sut.getDeckPointSum() shouldBe 0
        sut.receive(Card.of(CardRank.JACK))
        sut.getDeckPointSum() shouldBe 10
    }

    @Test
    internal fun `플레이어는 카드 점수 합계가 21점을 넘으면 버스트 된다`() {
        val sut = Challenger("A")
        sut.receive(Card.of(CardRank.JACK))
        sut.isBurst shouldBe false
        sut.receive(Card.of(CardRank.QUEEN))
        sut.isBurst shouldBe false
        sut.receive(Card.of(CardRank.KING))
        sut.isBurst shouldBe true
    }

    @Test
    internal fun `플레이어가 히트할 수 없을때 카드를 받으면 예외가 발생한다`() {
        val sut = Challenger("A")
        sut.receive(Card.of(CardRank.JACK))
        sut.receive(Card.of(CardRank.QUEEN))
        sut.receive(Card.of(CardRank.KING))
        assertThrows<IllegalStateException> { sut.receive(Card.of(CardRank.ACE)) }
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

        sut.receive(Card.of(CardRank.valueOf(firstCard)))
        sut.isBlackjack shouldBe false

        sut.receive(Card.of(CardRank.valueOf(secondCard)))
        sut.isBlackjack shouldBe expectedBlackjack
    }
}
