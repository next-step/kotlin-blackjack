package blackjack

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = ["홍길동", "한맹희"])
    internal fun `플레이어는 이름을 가진다`(value: String) {
        val sut = Player(
            name = value,
        )
        sut.name shouldBe value
    }

    @Test
    internal fun `플레이어는 카드를 받고 점수를 계산할 수 있다`() {
        val sut = Player("A")
        sut.point shouldBe 0
        sut.receive(Card.of(CardRank.JACK))
        sut.point shouldBe 10
    }

    @Test
    internal fun `플레이어는 카드 점수 합계가 21점 이하면 히트 가능하고 넘으면 버스트 된다`() {
        val sut = Player("A")
        sut.receive(Card.of(CardRank.JACK))
        sut.isBurst shouldBe false
        sut.canHit shouldBe true
        sut.receive(Card.of(CardRank.QUEEN))
        sut.isBurst shouldBe false
        sut.canHit shouldBe true
        sut.receive(Card.of(CardRank.KING))
        sut.isBurst shouldBe true
        sut.canHit shouldBe false
    }

    @Test
    internal fun `플레이어가 스테이(카드를 받지 않음)를 하면 더이상 히트 할 수 없다`() {
        val sut = Player("A")
        sut.canHit shouldBe true
        sut.stay()
        sut.canHit shouldBe false
    }

    @Test
    internal fun `플레이어가 히트할 수 없을때 카드를 받으면 예외가 발생한다`() {
        val sut = Player("A")
        sut.stay()
        assertThrows<IllegalStateException> { sut.receive(Card.of(CardRank.ACE)) }
    }
}
