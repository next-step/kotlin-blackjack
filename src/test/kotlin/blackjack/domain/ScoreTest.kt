package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreTest {
    @Test
    fun `Score는 카드의 점수의 합을 나타낸다`() {
        val ace = Card(CardCharacter.ACE, CardShape.CLUB)
        val ten = Card(CardCharacter.TEN, CardShape.CLUB)
        val score = Score(Cards(mutableSetOf(ace, ten)))
        assertThat(score.value).isEqualTo(21)
    }
}
