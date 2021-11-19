package domain.player

import domain.card.CardGenerator
import domain.card.MockedCardGenerator
import exception.IllegalDrawException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class DealerTest {
    private lateinit var cardGenerator: CardGenerator
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        cardGenerator = MockedCardGenerator()
        dealer = Dealer(cardGenerator)
    }

    @DisplayName("Dealer 의 이름은 딜러이다.")
    @Test
    fun name() {
        val expectedName = "딜러"
        assertThat(dealer.name()).isEqualTo(expectedName)
    }

    @DisplayName("Dealer 의 점수가 16점 이하이면, 딜러는 카드를 뽑을 수 있다.")
    @ParameterizedTest
    @CsvSource("1,13", "2,14", "3,16", "6,12", "7,15")
    fun drawable(times: Int, expectedScore: Int) {
        repeat(times) { dealer.play(true, cardGenerator) }
        assertAll(
            { assertThat(dealer.isDrawable()).isTrue },
            { assertThat(dealer.score()).isEqualTo(expectedScore) }
        )
    }

    @DisplayName("Dealer 의 점수가 16점 초과이면, 딜러는 카드를 뽑을 수 없다.")
    @ParameterizedTest
    @CsvSource("4,18", "5,20", "8,18", "9,21")
    fun notDrawable(times: Int, expectedScore: Int) {
        repeat(times) { dealer.play(true, cardGenerator) }
        assertAll(
            { assertThat(dealer.isDrawable()).isFalse },
            { assertThat(dealer.score()).isEqualTo(expectedScore) },
            {
                assertThatExceptionOfType(IllegalDrawException::class.java)
                    .isThrownBy { dealer.draw(cardGenerator) }
            }
        )
    }
}
