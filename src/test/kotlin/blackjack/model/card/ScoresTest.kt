package blackjack.model.card

import blackjack.dummy.toCardSet
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class ScoresTest {

    @ParameterizedTest
    @CsvSource(
        "'JS,QD,AH,AC','22,32,42',22",
        "'JS,QD,AH,2C','23,33',23",
    )
    fun `버스트 점수 테스트`(
        cardListString: String,
        scoreListString: String,
        finalScore: Int,
    ) {
        val cardSet = cardListString.toCardSet()
        val expectedScoreList = scoreListString.split(",").map { it.toInt() }
        val state = State.of(cardSet)
        val actualScoreList = Scores.of(cardSet).toList()

        org.junit.jupiter.api.assertAll(
            { Assertions.assertThat(actualScoreList).isEqualTo(expectedScoreList) },
            { Assertions.assertThat(state.finalScore).isEqualTo(finalScore) },
            { Assertions.assertThat(state).isInstanceOf(State.Bust::class.java) }
        )
    }

    @ParameterizedTest
    @CsvSource(
        "'AS,AC,2H','4,14,24',14",
        "'2S,3D,2H,3C','10',10",
        "'AS,AD,AH,AC','4,14,24,34,44',14",
    )
    fun `러닝 점수 테스트`(
        cardListString: String,
        scoreListString: String,
        finalScore: Int,
    ) {
        val cardSet = cardListString.toCardSet()
        val expectedScoreList = scoreListString.split(",").map { it.toInt() }
        val state = State.of(cardSet)
        val actualScoreList = Scores.of(cardSet).toList()

        org.junit.jupiter.api.assertAll(
            { Assertions.assertThat(actualScoreList).isEqualTo(expectedScoreList) },
            { Assertions.assertThat(state.finalScore).isEqualTo(finalScore) },
            { Assertions.assertThat(state).isInstanceOf(State.Running::class.java) }
        )
    }
}
