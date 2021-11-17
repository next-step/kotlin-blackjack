package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class CardNumberTest {
    @ParameterizedTest
    @ValueSource(strings = ["J", "Q", "K"])
    fun `J, Q, K 를 isRoyaFamily() 메소드의 인자로 입력하면 true를 리턴한다`(text: String) {
        assertThat(CardNumber.isRoyalFamily(text)).isTrue
    }

    @Test
    fun `A 문자열을 isAce() 메소드의 인자로 입력하면 true를 리턴한다`() {
        assertThat(CardNumber.isAce("A")).isTrue
    }
}
