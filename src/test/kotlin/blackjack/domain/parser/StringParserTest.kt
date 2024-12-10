package blackjack.domain.parser

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StringParserTest {

    @Test
    fun `플레이어 이름들을 입력하면 파싱할 수 있다`() {
        // given
        val str = "jay, jihoi, pobi"

        // when
        val result = StringParser.parse(str)

        // then
        assertThat(result.size).isEqualTo(3)
        assertThat(result[0]).isEqualTo("jay")
    }

}
