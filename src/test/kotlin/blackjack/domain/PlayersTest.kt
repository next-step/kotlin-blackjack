package blackjack.domain

import blackjack.domain.Game.Companion.INITIAL_CARDS_COUNT
import blackjack.model.Bet
import blackjack.model.Card
import blackjack.model.CardShape
import blackjack.model.CardType
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class PlayersTest {
    private val randomBet = (1..1000000).random().let(::Bet)

    @ParameterizedTest
    @ValueSource(ints = [2, 5, 8])
    fun `블랙잭 참가 플에이어는 최소 2명 이상 최대 8명 이하이다`(createPlayCount: Int) {
        val participants = mutableListOf<Player>().apply {
            repeat(createPlayCount) {
                add(FakePlayer())
            }
        }
        val players = Players(participants)
        assertThat(players).isNotNull
    }

    @Test
    fun `블랙잭 참가 플에이어가 2명 미만시 에러가 발생한다`() {
        val participants = mutableListOf<Player>().apply {
            add(FakePlayer())
        }
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy { Players(participants) }
    }

    @Test
    fun `블랙잭 참가 플에이어가 8명 초과시 에러가 발생한다`() {
        val participants = mutableListOf<Player>().apply {
            repeat(9) {
                add(FakePlayer())
            }
        }
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Players(participants)
        }
    }

    @Test
    fun `모든 블랙잭 참가들이 카드를 2장씩 받으면 게임 시작할 준비가 되었다`() {
        val participants = mutableListOf<GamePlayer>().apply {
            repeat(2) { count ->
                add(GamePlayer("$count", randomBet).apply {
                    repeat(INITIAL_CARDS_COUNT) {
                        play.draw(Card(CardType.SIX, CardShape.CLOVER))
                    }
                })
            }
        }
        val players = Players(participants)
        assertThat(players.allReadyToPlay()).isTrue
    }

    @Test
    fun `모든 블랙잭 참가들 중 한명이라도 카드를 2장 미만으로 카드 받으면 게임 시작할 준비가 되어있지 않다`() {
        val participants = mutableListOf<GamePlayer>().apply {
            repeat(2) { count ->
                repeat(2) { count ->
                    add(GamePlayer("$count", randomBet).apply {
                        repeat(INITIAL_CARDS_COUNT) {
                            if (count == 1) play.draw(Card(CardType.SIX, CardShape.CLOVER))
                        }
                    })
                }
            }
        }
        val players = Players(participants)
        assertThat(players.allReadyToPlay()).isFalse
    }

    @Test
    fun `모든 플레이들의 수익률을 확인할 수 있다`() {
        // given
        val dealer = FakeDealer()
        val participants = mutableListOf<Player>().apply {
            repeat(2) { count ->
                add(FakePlayer(name = count.toString(), profit = 1000.0))
            }
        }

        // when
        val players = Players(participants)
        val result = players.allPlayerProfits(dealer)

        // then
        assertThat(result.value.sumOf { it.profit }).isEqualTo(2000.0)
    }
}
