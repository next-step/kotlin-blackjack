package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardTest {

    @Test
    internal fun `에이스 빌드하면 네종류의 에이스 카드가 생성된다`() {
        val blackjack = deck {
            ace()
        }.build()
        assertThat((1..4).map { blackjack.next() })
            .allSatisfy {
                assertThat(it.name).isEqualTo("A")
                assertThat(it.symbol).isIn(*Symbol.values())
            }
    }

    @Test
    internal fun `노멀카드를 빌드하면 2~10 사이의 카드가 생성된다`() {
        val blackjack = deck {
            normal(2..10)
        }.build()
        assertThat((1..36).map { blackjack.next() })
            .allSatisfy {
                assertThat(it.name.toInt()).isBetween(2, 10)
                assertThat(it.symbol).isIn(*Symbol.values())
            }
    }

    @Test
    internal fun `잭, 퀸, 킹을 빌드하면 잭, 퀸 킹 카드가 생성된다`() {
        val blackjack = deck {
            jack()
            queen()
            king()
        }.build()
        assertThat((1..12).map { blackjack.next() })
            .allSatisfy {
                assertThat(it.name).isIn("J", "Q", "K")
                assertThat(it.symbol).isIn(*Symbol.values())
            }
    }

    @Test
    internal fun `비정상 카드이름`() {
        assertThrows<IllegalArgumentException> { Card("1", Symbol.HEARTS) }
    }
}
