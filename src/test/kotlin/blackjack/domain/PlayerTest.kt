package blackjack.domain

import io.kotest.matchers.throwable.shouldHaveMessage
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class PlayerTest {

    @Test
    fun `카드 추가`() {
        val player = Player("디디")

        val newCard = Card(CardNumber.ACE, CardSuit.SPADE)
        player.addCard(newCard)

        assertThat(player.hand.cards).containsExactly(newCard)
    }

    @ParameterizedTest
    @MethodSource
    fun `스코어 계산 후 상태 조회`(hand: Hand, status: Status) {
        val player = Player("디디", hand)

        assertThat(player.status).isEqualTo(status)
    }

    @Test
    fun `플레이어 이름이 공백이면 예외`() {
        assertThrows<IllegalArgumentException> { Player("") }
            .shouldHaveMessage("플레이어 이름은 공백일 수 없습니다")
    }

    companion object {
        @JvmStatic
        fun `스코어 계산 후 상태 조회`(): List<Arguments> {
            return listOf(
                Arguments.arguments(
                    Hand(mutableListOf(Card(CardNumber.ACE, CardSuit.CLOVER), Card(CardNumber.JACK, CardSuit.CLOVER))),
                    Status.BLACKJACK
                ),
                Arguments.arguments(
                    Hand(mutableListOf(Card(CardNumber.JACK, CardSuit.CLOVER), Card(CardNumber.JACK, CardSuit.CLOVER), Card(CardNumber.ACE, CardSuit.CLOVER))),
                    Status.HIT
                ),
                Arguments.arguments(
                    Hand(mutableListOf(Card(CardNumber.ACE, CardSuit.CLOVER), Card(CardNumber.NINE, CardSuit.CLOVER))),
                    Status.HIT
                ),
                Arguments.arguments(
                    Hand(mutableListOf(Card(CardNumber.TWO, CardSuit.CLOVER), Card(CardNumber.JACK, CardSuit.CLOVER), Card(CardNumber.KING, CardSuit.CLOVER))),
                    Status.BUST
                )
            )
        }
    }
}
