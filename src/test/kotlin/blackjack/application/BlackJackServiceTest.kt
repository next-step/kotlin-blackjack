package blackjack.application

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardSuit
import blackjack.domain.Deck
import blackjack.domain.Hand
import blackjack.domain.Player
import blackjack.domain.Score
import blackjack.domain.Status
import blackjack.view.GameResult
import io.kotest.matchers.throwable.shouldHaveMessage
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.assertj.core.groups.Tuple
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class BlackJackServiceTest {

    @Test
    fun `플레이어 생성`() {
        assertThat(BlackJackService.createPlayers(listOf("디디", "카일", "범블비")))
            .extracting(Player::name, Player::status)
            .containsExactly(
                Tuple.tuple("디디", Status.HIT),
                Tuple.tuple("카일", Status.HIT),
                Tuple.tuple("범블비", Status.HIT)
            )
    }

    @ParameterizedTest
    @MethodSource
    fun `플레이어 수가 1 미만 26 초과인 경우 예외`(names: List<String>) {
        assertThrows<IllegalArgumentException> { BlackJackService.createPlayers(names) }
            .shouldHaveMessage("플레이어 수는 1 이상 26 이하여야 합니다.")
    }

    @Test
    fun `게임결과 계산`() {
        val players = listOf(
            Player(
                "카일",
                Hand(mutableListOf(Card(CardNumber.ACE, CardSuit.SPADE), Card(CardNumber.JACK, CardSuit.SPADE)))
            ),
            Player(
                "디디",
                Hand(mutableListOf(Card(CardNumber.ACE, CardSuit.CLOVER), Card(CardNumber.ACE, CardSuit.HEART)))
            ),
            Player(
                "범블비",
                Hand(
                    mutableListOf(
                        Card(CardNumber.ACE, CardSuit.DIAMOND),
                        Card(CardNumber.JACK, CardSuit.CLOVER),
                        Card(CardNumber.KING, CardSuit.SPADE)
                    )
                )
            ),
        )

        val results = BlackJackService.calculate(players)
        assertThat(results.result).extracting(GameResult::name, GameResult::score)
            .containsExactlyInAnyOrder(
                tuple("카일", 21),
                tuple("디디", 12),
                tuple("범블비", 21),
            )
    }

    @Test
    fun `덱에서 카드를 드로우 한다`() {
        val player = Player("디디")
        val deck = Deck()

        BlackJackService.draw(player, deck, 2)

        assertThat(player.hand.cards).hasSize(2)
    }

    companion object {

        @JvmStatic
        fun `플레이어 수가 1 미만 26 초과인 경우 예외`(): List<Arguments> {
            return listOf(
                Arguments.arguments(listOf<String>()),
                Arguments.arguments(
                    listOf(
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1"
                    )
                ),
            )
        }
    }
}
