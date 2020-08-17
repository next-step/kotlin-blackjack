package blackjack.domain.result

import blackjack.domain.card.Cards
import blackjack.domain.player.Challenger
import blackjack.domain.player.Dealer
import blackjack.domain.player.PlayerInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GameResultTest {

    @Test
    fun `플레이어가 블랙잭인 경우 수익률 계산`() {
        // given
        val challenger = Challenger(PlayerInfo("malibin", 10_000), Cards("10", "A"))

        // then
        assertThat(GameResult.WIN.earnMoney(challenger)).isEqualTo(15_000)
    }

    @CsvSource("WIN,10000", "LOSE,-10000", "DRAW,0")
    @ParameterizedTest
    fun `각 결과에 대한 수익률 계산`(gameResult: GameResult, expectedMoney: Int) {
        // given
        val challenger = Challenger(PlayerInfo("malibin", 10_000), Cards("9", "8"))

        // then
        assertThat(gameResult.earnMoney(challenger)).isEqualTo(expectedMoney)
    }

    @MethodSource("challengerCardsAndResult")
    @ParameterizedTest
    fun `게임 결과를 제대로 찾는지 확인`(challengerCards: Cards, gameResult: GameResult) {
        // given
        val dealer = Dealer(cards = Cards("10", "5"))
        val challenger = Challenger(PlayerInfo("malibin", 0), challengerCards)

        // then
        assertThat(GameResult.ofChallenger(dealer, challenger)).isEqualTo(gameResult)
    }

    companion object {
        @JvmStatic
        fun challengerCardsAndResult(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Cards("10", "10"), GameResult.WIN),
                Arguments.of(Cards("10", "5"), GameResult.DRAW),
                Arguments.of(Cards("10", "2"), GameResult.LOSE)
            )
        }
    }
}
