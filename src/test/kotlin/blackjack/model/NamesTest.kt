package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NamesTest {

    @Test
    fun `, 구분자로 이름을 분리한다`() {
        assertThat(Names.parse("laco,pobi"))
            .isEqualTo(
                Names(listOf(Name.valueOf("laco"), Name.valueOf("pobi")))
            )
    }
}
