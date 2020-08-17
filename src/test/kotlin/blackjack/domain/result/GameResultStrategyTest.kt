package blackjack.domain.result

import blackjack.domain.card.Cards
import blackjack.domain.player.Challenger
import blackjack.domain.player.Dealer
import blackjack.domain.player.PlayerInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GameResultStrategyTest {

    @MethodSource("challengerAndDealerCardsAndResult")
    @ParameterizedTest
    fun `플레이어가 이기는 경우 확인`(challengerCard: Cards, dealerCards: Cards, result: GameResult) {
        // given
        val challenger = Challenger(PlayerInfo("", 0), challengerCard)
        val dealer = Dealer(cards = dealerCards)
        val isWin = result == GameResult.WIN
        val resultStrategy: GameResultStrategy = WinResultStrategy()

        // then
        assertThat(resultStrategy.isMatch(dealer, challenger)).isEqualTo(isWin)
    }

    @MethodSource("challengerAndDealerCardsAndResult")
    @ParameterizedTest
    fun `플레이어가 지는 경우 확인`(challengerCard: Cards, dealerCards: Cards, result: GameResult) {
        val challenger = Challenger(PlayerInfo("", 0), challengerCard)
        val dealer = Dealer(cards = dealerCards)
        val isLose = result == GameResult.LOSE
        val resultStrategy: GameResultStrategy = LoseResultStrategy()

        // then
        assertThat(resultStrategy.isMatch(dealer, challenger)).isEqualTo(isLose)
    }

    @MethodSource("challengerAndDealerCardsAndResult")
    @ParameterizedTest
    fun `비기는 경우 확인`(challengerCard: Cards, dealerCards: Cards, result: GameResult) {
        val challenger = Challenger(PlayerInfo("", 0), challengerCard)
        val dealer = Dealer(cards = dealerCards)
        val isDraw = result == GameResult.DRAW
        val resultStrategy: GameResultStrategy = DrawResultStrategy()

        // then
        assertThat(resultStrategy.isMatch(dealer, challenger)).isEqualTo(isDraw)
    }

    companion object {
        @JvmStatic
        fun challengerAndDealerCardsAndResult(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Cards("10", "A"), Cards("10", "A"), GameResult.DRAW),
                Arguments.of(Cards("10", "5"), Cards("10", "5"), GameResult.DRAW),
                Arguments.of(Cards("10", "A"), Cards("10", "5"), GameResult.WIN),
                Arguments.of(Cards("10", "A"), Cards("10", "10", "10"), GameResult.WIN),
                Arguments.of(Cards("10", "10", "10"), Cards("10", "10", "10"), GameResult.WIN),
                Arguments.of(Cards("10", "10"), Cards("10", "5"), GameResult.WIN),
                Arguments.of(Cards("10", "10", "10"), Cards("10", "5"), GameResult.LOSE),
                Arguments.of(Cards("10", "5"), Cards("10", "10"), GameResult.LOSE)
            )
        }
    }
}
