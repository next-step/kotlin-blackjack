package blackjack.domain

import blackjack.domain.blackjackgame.GameStatus
import blackjack.domain.card.Card
import blackjack.domain.card.CardShape
import blackjack.domain.card.CardSymbol
import blackjack.domain.card.PlayerDeck
import blackjack.domain.judge.Loose
import blackjack.domain.judge.Win
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.UserSetting
import blackjack.domain.state.BlackJack
import blackjack.domain.state.Burst
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class BlackjackBettingTest {

    @Test
    fun `모두가 블랙잭 일 때`() {
        val gamers = allBlackjackGamers()
        val blackjackBetting = BlackjackBetting(gamers)
        val gameResult = blackjackBetting.getGameResult()
        assertAll("블랙잭 일 시 수익 확인", {
            assertThat(gameResult[0].money).isEqualTo(0)
            assertThat(gameResult[1].money).isEqualTo(0)
            assertThat(gameResult[2].money).isEqualTo(0)
        })
    }


    @Test
    fun `딜러가 버스트 인 경우 플레이어가 블랙잭인 경우 배팅 금액 확인`() {
        val gamers = getGamersWithDealerBurst()
        val blackjackBetting = BlackjackBetting(gamers)
        val gameResult = blackjackBetting.getGameResult()

        assertAll("배팅 금액 확인", {
            assertThat(gameResult[0].money).isEqualTo(1500)
            assertThat(gameResult[1].money).isEqualTo(3000)
            assertThat(gameResult[2].money).isEqualTo(-4500)
        })
    }

    private fun allBlackjackGamers() = listOf(
        Player(
            UserSetting("김성주", 1000),
            GameStatus(
                state = BlackJack(
                    PlayerDeck(
                        listOf(
                            Card(CardShape.CLOVER, CardSymbol.ACE),
                            Card(CardShape.CLOVER, CardSymbol.JACK)
                        )
                    )
                )
            )
        ),

        Player(
            UserSetting("김성주2", 2000),
            GameStatus(
                state = BlackJack(
                    PlayerDeck(
                        listOf(
                            Card(CardShape.HEART, CardSymbol.ACE),
                            Card(CardShape.HEART, CardSymbol.JACK)
                        )
                    )
                )
            )
        ),

        Dealer(
            gameStatus = GameStatus(
                state = BlackJack(
                    PlayerDeck(
                        listOf(
                            Card(CardShape.DIAMOND, CardSymbol.ACE),
                            Card(CardShape.DIAMOND, CardSymbol.JACK)
                        )
                    )
                )
            )
        ),
    )


    private fun getGamersWithDealerBurst() = listOf(
        Player(
            UserSetting("김성주", 1000),
            GameStatus(
                state = BlackJack(
                    PlayerDeck(
                        listOf(
                            Card(CardShape.CLOVER, CardSymbol.ACE),
                            Card(CardShape.CLOVER, CardSymbol.JACK)
                        )
                    )
                ),
                judgements = mutableListOf(Win())
            )
        ),

        Player(
            UserSetting("김성주2", 2000),
            GameStatus(
                state = BlackJack(
                    PlayerDeck(
                        listOf(
                            Card(CardShape.HEART, CardSymbol.ACE),
                            Card(CardShape.HEART, CardSymbol.JACK)
                        )
                    )
                ),
                judgements = mutableListOf(Win())
            )
        ),

        Dealer(
            gameStatus = GameStatus(
                state = Burst(
                    PlayerDeck(
                        listOf(
                            Card(CardShape.DIAMOND, CardSymbol.ACE),
                            Card(CardShape.DIAMOND, CardSymbol.JACK),
                            Card(CardShape.DIAMOND, CardSymbol.KING),
                            Card(CardShape.DIAMOND, CardSymbol.QUEEN),
                        )
                    )
                ),
                judgements = mutableListOf(Loose(), Loose())
            )
        ),
    )
}
