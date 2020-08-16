package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerResultTest {
    @Test
    fun get_win_draw_lose() {
        assertThat(DealerResult.WIN.getCount()).isEqualTo(0)
        assertThat(DealerResult.DRAW.getCount()).isEqualTo(0)
        assertThat(DealerResult.LOSE.getCount()).isEqualTo(0)
    }

    @Test
    fun dealer_win() {
        DealerResult.WIN.addCount()

        assertThat(DealerResult.WIN.getCount()).isEqualTo(1)
    }

    @Test
    fun reset_result() {
        DealerResult.WIN.addCount()

        DealerResult.resetResult()

        assertThat(DealerResult.WIN.getCount()).isEqualTo(0)
    }
}
