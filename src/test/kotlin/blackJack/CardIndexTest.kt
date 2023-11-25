package blackJack

import CardIndex
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardIndexTest {

    @Test
    fun `인덱스가 생성되고, 인덱스를 요청할 때, 현재 인덱스를 반환한다`() {
        // given : 인덱스가 생성된다. 인덱스는 초기값은 0이다.
        val caredIndex = CardIndex()

        // when : 인덱스를 요청한다.
        val actual = caredIndex.getIndex()

        // then : 현재 인덱스를 반환한다.
        assertThat(actual).isEqualTo(0)
    }

    @Test
    fun `인덱스가 생성되고, 인덱스 증가 요청할 때, 인덱스가 1 증가된다`() {
        // given : 인덱스가 생성된다.
        val caredIndex = CardIndex()
        val prevIndex = caredIndex.getIndex()

        // when : 인덱스 증가 요청을 한다.
        caredIndex.increaseIndex()
        val actual = caredIndex.getIndex()

        // then : 인덱스가 1 증가된다.
        assertThat(actual).isEqualTo(prevIndex + 1)
    }
}
