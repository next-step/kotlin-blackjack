package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultTest {
    @Test
    fun make_result() {
        val players = Players(listOf("joo", "han"))
        val dealer = Dealer()

        val result = Result(players, dealer)

        assertThat(result.dealerResult["수익"]).isEqualTo(0)
        assertThat(result.playerResult).isNotEmpty()
    }
}
