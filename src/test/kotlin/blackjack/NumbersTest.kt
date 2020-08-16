package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class NumbersTest {
    @DisplayName("11을 더했을 때 총합이 21보다 크면 ACE 값을 1로 계산한다.")
    @ParameterizedTest
    @ValueSource(ints = [11, 12, 13, 14])
    fun `when total is above 21 when 11 is added than ACE value is calculated as 1`(totalScore: Int) {
        assertThat(Numbers.ofScore(totalScore, Numbers.ACE)).isEqualTo(1)
    }

    @DisplayName("11을 더했을 때 총합이 21보다 작으면 ACE 값을 11로 계산한다.")
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
    fun `when total is less than 21 when 11 is added than the ACE value is calculated as 11`(totalScore: Int) {
        assertThat(Numbers.ofScore(totalScore, Numbers.ACE)).isEqualTo(11)
    }
}
