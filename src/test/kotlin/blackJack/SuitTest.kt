package blackJack

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class SuitTest {

    @Test
    fun `트럼프 카드에 존재하지 않는 무늬가 있다면, 트럼프 카드 무늬를 생성할 때, 예외를 던진다`() {
        // given :  트럼프 카드에 존재하지 않는 무늬를 받는다.
        val suit = "circle"

        // when : 트럼프 카드의 무늬를 만든다.
        val cardSuit = runCatching { Suit.from(suit) }.exceptionOrNull()

        // then : 예외를 던진다.
        Assertions.assertThat(cardSuit).isInstanceOf(IllegalArgumentException::class.java)
    }
}
