package blackjack.view.output.converter

import blackjack.domain.MatchStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class MatchStatusConverterTest {
    @Test
    fun `MatchStatusConverter는 MatchStatus를 출력을 위한 문자열로 변환해 반환한다`() {
        val dealer = MatchStatus.Dealer(2, 1, 1)
        assertAll(
            { assertThat(MatchStatusConverter.convert(dealer)).isEqualTo("2승 1무 1패") },
            { assertThat(MatchStatusConverter.convert(MatchStatus.Win)).isEqualTo("승") },
            { assertThat(MatchStatusConverter.convert(MatchStatus.Push)).isEqualTo("무") },
            { assertThat(MatchStatusConverter.convert(MatchStatus.Lose)).isEqualTo("패") }
        )
    }
}
