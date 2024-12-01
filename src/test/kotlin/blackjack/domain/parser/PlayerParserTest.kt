package blackjack.domain.parser

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerParserTest {

    @Test
    fun `플레이어 문자열을 입력하면 파싱할 수 있다`() {
        // given
        val str = "jay, jihoi, pobi"

        // when
        val result = PlayerParser.parse(str)

        // then
        assertThat(result.size).isEqualTo(3)
        assertThat(result[0].name).isEqualTo("jay")
    }

}
