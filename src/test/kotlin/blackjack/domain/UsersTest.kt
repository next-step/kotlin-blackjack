package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * Created by Jaesungchi on 2022.06.07..
 */
class UsersTest {

    @ParameterizedTest
    @ValueSource(strings = ["jason,link", "james, link"])
    fun `입력받은 이름들을 쉼표로 잘 나눈다`(source: String) {
        assertThat(Users.of(source).getSize()).isEqualTo(2)
    }
}
