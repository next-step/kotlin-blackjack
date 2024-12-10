package blackjack.domain.result

import blackjack.domain.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class GameResultTest {

    @ParameterizedTest
    @MethodSource("provideTestParam")
    fun `게임 결과 테스트`(param: GameResultTestParam) {
        // when
        val result = GameResult("Dealer", param.playersResult)

        // then
        assertThat(result.dealerProfit).isEqualTo(param.dealerProfit)
    }

    companion object {
        @JvmStatic
        fun provideTestParam(): List<GameResultTestParam> {
            return listOf(
                GameResultTestParam(
                    listOf(
                        PlayerGameResult(
                            Player(name = "jay", bettingAmount = 10000),
                            resultType = GameResultType.WIN,
                        ),
                        PlayerGameResult(
                            Player(name = "jihoi", bettingAmount = 20000),
                            resultType = GameResultType.LOSE,
                        ),
                    ),
                    10000,
                ),
                GameResultTestParam(
                    listOf(
                        PlayerGameResult(
                            Player(name = "jay", bettingAmount = 20000),
                            resultType = GameResultType.BLACKJACK_WIN,
                        ),
                        PlayerGameResult(
                            Player(name = "jihoi", bettingAmount = 20000),
                            resultType = GameResultType.LOSE,
                        ),
                    ),
                    -10000,
                ),
                GameResultTestParam(
                    listOf(
                        PlayerGameResult(
                            Player(name = "jay", bettingAmount = 20000),
                            resultType = GameResultType.BLACKJACK_WIN,
                        ),
                        PlayerGameResult(
                            Player(name = "jihoi", bettingAmount = 20000),
                            resultType = GameResultType.PUSH,
                        ),
                    ),
                    -30000,
                ),
            )
        }
    }

    data class GameResultTestParam(
        val playersResult: List<PlayerGameResult>,
        val dealerProfit: Int,
    )

}
