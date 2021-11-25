package blackject

import blackject.model.Amount
import blackject.model.Player
import blackject.model.ResultType
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    @DisplayName("게임에서 이긴 사용자의 수익률 계산")
    fun `check profit of win user`() {
        val money = Amount(100.0)
        val result = ResultType.Win

        val profit = Player(result, money).getProfit()

        Assertions.assertThat(profit).isEqualTo(100)
    }

    @Test
    @DisplayName("게임에서 진 사용자의 수익률 계산")
    fun `check profit of lose user`() {
        val money = Amount(100.0)
        val result = ResultType.Lose

        val profit = Player(result, money).getProfit()

        Assertions.assertThat(profit).isEqualTo(-100)
    }

    @Test
    @DisplayName("BlackJack 사용자의 수익률 계산")
    fun `check profit of blackjack user`() {
        val money = Amount(100.0)
        val result = ResultType.BlackJack

        val profit = Player(result, money).getProfit()

        Assertions.assertThat(profit).isEqualTo(150)
    }

    @Test
    @DisplayName("Bust 사용자의 수익률 계산")
    fun `check profit of bust user`() {
        val money = Amount(100.0)
        val result = ResultType.Bust

        val profit = Player(result, money).getProfit()

        Assertions.assertThat(profit).isEqualTo(-100)
    }
}
