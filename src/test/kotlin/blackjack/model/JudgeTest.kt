package blackjack.model

import blackjack.model.gamer.Dealer
import blackjack.model.gamer.Player
import blackjack.model.state.BlackJack
import blackjack.model.state.Bust
import blackjack.model.state.Hit
import blackjack.model.state.State
import blackjack.model.trump.Card
import blackjack.model.trump.CardNumber
import blackjack.model.trump.Cards
import blackjack.model.trump.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class JudgeTest {

    @ParameterizedTest
    @MethodSource("gamerOpponentProvider")
    fun calculateRevenue(playerState: State, opponentState: State, playerBetMoney: BetMoney, playerRevenue: BetMoney) {
        val gamer = Player("player", playerBetMoney).apply {
            state = playerState
        }

        val opponent = Dealer().apply {
            state = opponentState
        }

        assertThat(Judge.calculateRevenue(gamer, opponent)).isEqualTo(playerRevenue)
    }

    companion object {
        @JvmStatic
        fun gamerOpponentProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        Hit(Cards(Card(CardNumber.EIGHT, Suit.DIAMOND), Card(CardNumber.TEN, Suit.DIAMOND))),
                        Hit(Cards(Card(CardNumber.TEN, Suit.CLUB), Card(CardNumber.NINE, Suit.CLUB))),
                        BetMoney(10),
                        BetMoney(-10)
                    )
                },
                Arguments {
                    arrayOf(
                        Hit(Cards(Card(CardNumber.EIGHT, Suit.DIAMOND), Card(CardNumber.TEN, Suit.DIAMOND))),
                        Hit(Cards(Card(CardNumber.TWO, Suit.CLUB), Card(CardNumber.NINE, Suit.CLUB))),
                        BetMoney(10),
                        BetMoney(10)
                    )
                },
                Arguments {
                    arrayOf(
                        Bust(Cards(Card(CardNumber.EIGHT, Suit.DIAMOND), Card(CardNumber.TEN, Suit.DIAMOND), Card(CardNumber.FOUR, Suit.HEART))),
                        Hit(Cards(Card(CardNumber.TEN, Suit.CLUB), Card(CardNumber.TWO, Suit.CLUB))),
                        BetMoney(10),
                        BetMoney(-10)
                    )
                },
                Arguments {
                    arrayOf(
                        Hit(Cards(Card(CardNumber.EIGHT, Suit.DIAMOND), Card(CardNumber.TEN, Suit.DIAMOND), Card(CardNumber.ACE, Suit.SPADE))),
                        Hit(Cards(Card(CardNumber.THREE, Suit.CLUB), Card(CardNumber.NINE, Suit.CLUB))),
                        BetMoney(10),
                        BetMoney(10)
                    )
                },
                Arguments {
                    arrayOf(
                        BlackJack(Cards(Card(CardNumber.ACE, Suit.DIAMOND), Card(CardNumber.JACK, Suit.DIAMOND))),
                        Hit(Cards(Card(CardNumber.TWO, Suit.CLUB), Card(CardNumber.NINE, Suit.CLUB))),
                        BetMoney(10),
                        BetMoney(15)
                    )
                }
            )
        }
    }
}
