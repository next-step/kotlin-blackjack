package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@DisplayName("참가자(Player)")
internal class PlayerTest {

    @ParameterizedTest(name = "입력값 : {0}")
    @ValueSource(strings = ["a", "b", "c", "ab", "abc"])
    fun `비거나 공백이 아닌 문자열로 사용자를 만들 수 있다`(name: String) {
        val player = Player.fromName(name)

        assertAll(
            { assertThat(player).isNotNull },
            { assertThat(player).isExactlyInstanceOf(Player::class.java) }
        )
    }

    @ParameterizedTest(name = "입력값 : {0}")
    @ValueSource(strings = ["a", "b", "c", "ab", "abc"])
    fun `사용자의 이름을 반환한다`(name: String) {
        val player = Player.fromName(name)

        assertThat(player.name).isEqualTo(name)
    }

    @Test
    fun `사용자는 기본적으로 플레잉중인 상태다`() {
        val player = Player.fromName("test")

        assertThat(player.isFinished()).isFalse
    }

    @Test
    fun `게임을 진행하고자 한다면 플레잉중인 상태다`() {
        val player = Player.fromName("test")
        val playingPlayer = player.continuePlay("y")

        assertThat(playingPlayer.isFinished()).isFalse
    }

    @Test
    fun `더이상 게임을 진행하지 않는다면 플레잉이 끝난 상태다`() {
        val player = Player.fromName("test")
        val finishedPlayer = player.continuePlay("n")

        assertThat(finishedPlayer.isFinished()).isTrue
    }

    @Test
    fun `보유한 카드들의 스코어가 21을 초과하지 않으면 플레잉 중인 상태다`() {
        val player = Player.fromName("test")
        val externalPlayingCards = listOf(
            Card(Suit.CLUB, Denomination.ACE),
            Card(Suit.CLUB, Denomination.TWO),
            Card(Suit.CLUB, Denomination.THREE),
            Card(Suit.CLUB, Denomination.FOUR),
            Card(Suit.CLUB, Denomination.FIVE),
            Card(Suit.CLUB, Denomination.SIX)
        )
        val finishedPlayer = player + externalPlayingCards
        assertThat(finishedPlayer.isFinished()).isFalse
    }

    @Test
    fun `보유한 카드들의 스코어가 21을 초과하면 플레잉이 끝난 상태로 바뀐다`() {
        val player = Player.fromName("test")
        val externalPlayingCards = listOf(
            Card(Suit.CLUB, Denomination.ACE),
            Card(Suit.CLUB, Denomination.TWO),
            Card(Suit.CLUB, Denomination.THREE),
            Card(Suit.CLUB, Denomination.FOUR),
            Card(Suit.CLUB, Denomination.FIVE),
            Card(Suit.CLUB, Denomination.SEVEN)
        )
        val finishedPlayer = player + externalPlayingCards
        assertThat(finishedPlayer.isFinished()).isTrue
    }
}
