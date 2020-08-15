package blackjack

import blackjack.model.status.Score
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ScoreTest {

    @DisplayName(value = "점수가 21이 넘을 경우, burst이다.")
    @Test
    fun checkIsBurst() {
        Assertions.assertThat(Score(50).isBurst()).isTrue()
    }

    @DisplayName(value = "점수가 21이 넘지 않을 경우, burst가 아니다.")
    @Test
    fun checkIsNotBurst() {
        Assertions.assertThat(Score(10).isBurst()).isFalse()
    }

    @DisplayName(value = "점수가 21 경우, BlackJack이다.")
    @Test
    fun checkIsBlackJack() {
        Assertions.assertThat(Score(Score.BLACKJACK).isBlackJack()).isTrue()
    }
}
