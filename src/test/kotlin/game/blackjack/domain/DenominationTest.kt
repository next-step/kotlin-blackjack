package game.blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("카드 등급")
internal class DenominationTest {

    @Test
    fun `단순 합 계산`() {
        val denomination = Denomination.TEN
        assertThat(denomination.score).isEqualTo(Score(10))
    }
}
