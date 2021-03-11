package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ResultTest {
    private val playerResults = listOf(Pair("a", Result.WIN), Pair("b", Result.LOSE), Pair("c", Result.DRAW))

    @DisplayName("플레이어 결과를 입력하면 그에 따른 딜러 결과 목록을 보여준다.")
    @Test
    fun getDealerResult() {
        assertThat(Result.getDealerResult(playerResults)).isEqualTo(listOf(Result.LOSE, Result.WIN, Result.DRAW))
    }
}
