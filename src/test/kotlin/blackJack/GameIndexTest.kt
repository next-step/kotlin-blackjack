package blackJack

import GameIndex
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameIndexTest {

    private var maxIndex = 52

    @Test
    fun `인덱스가 생성되고, 인덱스를 요청할 때, 현재 인덱스를 반환한다`() {
        // given : 인덱스가 생성된다. 인덱스는 초기값은 0이다.
        val caredIndex = GameIndex(maxIndex = maxIndex)

        // when : 인덱스를 요청한다.
        val actual = caredIndex.index

        // then : 현재 인덱스를 반환한다.
        assertThat(actual).isEqualTo(0)
    }

    @Test
    fun `인덱스가 생성되고, 인덱스 증가 요청할 때, 인덱스가 1 증가된다`() {
        // given : 인덱스가 생성된다.
        val caredIndex = GameIndex(maxIndex = maxIndex)
        val prevIndex = caredIndex.index

        // when : 인덱스 증가 요청을 한다.
        caredIndex.increaseIndex()
        val actual = caredIndex.index

        // then : 인덱스가 1 증가된다.
        assertThat(actual).isEqualTo(prevIndex + 1)
    }

    @Test
    fun `maxIndex까지 도달하고, 인덱스 증가 요청을 할때, 예외를 던진다`() {
        // given : max인덱스 값을 설정하여 생성 생성,
        val gameIndex = GameIndex(maxIndex = maxIndex)
        // maxIndex까지 도달한다
        repeat(maxIndex - 1) {
            gameIndex.increaseIndex()
        }

        // when : 인덱스 증가 요청을 한다
        val actual = runCatching { gameIndex.increaseIndex() }.exceptionOrNull()

        // then : 예외를 던진다.
        assertThat(actual).isInstanceOf(IllegalArgumentException::class.java)
    }
}
