package domain.card

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class RandomCardGeneratorTest {
    lateinit var cardGenerator: CardGenerator

    @BeforeEach
    fun setUp() {
        cardGenerator = RandomCardGenerator()
    }

    @DisplayName("생성되는 카드에는 중복이 없어야 한다.")
    @Test
    fun noDuplication() {
        val cards = (1..CARD_SIZE).map { cardGenerator.getCard() }
        assertThat(cards.distinct().size)
            .isEqualTo(CARD_SIZE)
    }

    @DisplayName("generator 가 생성할 수 있는 것보다 더 많이 생성하면 예외가 발생한다.")
    @Test
    fun exhausted() {
        Assertions.assertThatExceptionOfType(NoSuchElementException::class.java)
            .isThrownBy { repeat(CARD_SIZE + 1) { cardGenerator.getCard() } }
    }

    companion object {
        private const val CARD_SIZE = 52;
    }
}
