package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ProfitTest {
    private lateinit var profit: Profit

    @BeforeEach
    fun setUp() {
        profit = Profit(1000)
    }

    @Test
    fun `원금 만큼의 수익을 낸다`() {
        // when
        val actual = profit.earnAsMuchAsStake()

        // given
        assertThat(actual).isEqualTo(Profit(1000))
    }

    @Test
    fun `원금을 모두 잃는다`() {
        // when
        val actual = profit.loseAll()

        // given
        assertThat(actual).isEqualTo(Profit(-1000))
    }

    @Test
    fun `아무 이득이 없다`() {
        // when
        val actual = profit.gainNothing()

        // given
        assertThat(actual).isEqualTo(Profit(0))
    }

    @DisplayName("블랙잭일 때 배팅금액은 1.5배가 된다, 수익은 0.5배")
    @Test
    fun `블랙잭일 때 정해진 배율만큼 번다`() {
        // when
        val actual = profit.earnWhenIsBlackJack()

        // given
        assertThat(actual).isEqualTo(Profit(500))
    }
}
