package blackjack

import blackjack.model.status.Finish
import blackjack.model.status.Score
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ScoreTest {

    @DisplayName(value = "점수가 21이 넘을 경우, burst이다.")
    @Test
    fun checkIsBurst() {
        assertThat(Score(50).isBurst()).isTrue()
    }

    @DisplayName(value = "점수가 21이 넘지 않을 경우, burst가 아니다.")
    @Test
    fun checkIsNotBurst() {
        assertThat(Score(10).isBurst()).isFalse()
    }

    @DisplayName(value = "점수가 21 경우, BlackJack이다.")
    @Test
    fun checkIsBlackJack() {
        assertThat(Score(Score.BLACKJACK).isBlackJack()).isTrue()
    }

    @DisplayName(value = "Burst인 경우, 딜러가 Burst여부와 상관없이, 패배")
    @Test
    fun checkGameResultDefeatBurstCase() {
        val dealerScore = Score(23)
        assertThat(Score(23).getGameResult(dealerScore))
            .isSameAs(Finish.DEFEAT)
    }

    @DisplayName(value = "Dealer보다 점수가 낮을 경우, 패배")
    @Test
    fun checkGameResultDefeatSmallerThanDealer() {
        val dealerScore = Score(20)
        assertThat(Score(1).getGameResult(dealerScore))
            .isSameAs(Finish.DEFEAT)
    }

    @DisplayName(value = "Dealer보다 점수가 클 경우 승리")
    @Test
    fun checkGameResultWinBiggerThanDealer() {
        val dealerScore = Score(1)
        assertThat(Score(20).getGameResult(dealerScore))
            .isSameAs(Finish.WIN)
    }

    @DisplayName(value = "Dealer가 burst인 경우, 승리")
    @Test
    fun checkGameResultWinDealerBurst() {
        val dealerScore = Score(23)
        assertThat(Score(1).getGameResult(dealerScore))
            .isSameAs(Finish.WIN)
    }

    @DisplayName(value = "Dealer와 점수가 같다면, 무승부")
    @Test
    fun checkGameResultDrawSameScore() {
        val dealerScore = Score(21)
        assertThat(Score(21).getGameResult(dealerScore))
            .isSameAs(Finish.DRAW)
    }
}
