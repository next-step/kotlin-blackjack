package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerTest {
    @Test
    fun `Player는 이름과 손패, 상태를 보관한다`() {
        val player = Player("이름")

        assertAll(
            { assertThat(player.name).isEqualTo("이름") },
            { assertThat(player.hands.cards).isEmpty() },
            { assertThat(player.state).isInstanceOf(PlayerState.Start::class.java) }
        )
    }

    @Test
    fun `receive를 통해 카드를 받아 손패에 추가할 수 있다`() {
        val player = Player("이름")
        player.receive(
            PlayingCards.from(
                listOf(
                    PlayingCard.of(Suit.CLUBS, CardNumber.JACK),
                    PlayingCard.of(Suit.CLUBS, CardNumber.ACE)
                )
            )
        )

        assertAll(
            { assertThat(player.hands.cards).hasSize(2) },
            { assertThat(player.state).isInstanceOf(PlayerState.Blackjack::class.java) }
        )
    }

    @Test
    fun `finish를 통해 더 이상 카드를 받지 않겠다는 의사를 나타낼 수 있다`() {
        val player = Player("이름").apply {
            receive(
                PlayingCards.from(
                    listOf(
                        PlayingCard.of(Suit.CLUBS, CardNumber.JACK),
                        PlayingCard.of(Suit.CLUBS, CardNumber.KING)
                    )
                )
            )
        }
        player.finish()

        assertThat(player.state).isInstanceOf(PlayerState.Stay::class.java)
    }

    @Test
    fun `isReceivable을 통해 플레이어가 카드를 받을 수 있는 상태인지 알 수 있다`() {
        val player = Player("이름").apply {
            receive(
                PlayingCards.from(
                    listOf(
                        PlayingCard.of(Suit.CLUBS, CardNumber.JACK),
                        PlayingCard.of(Suit.CLUBS, CardNumber.KING)
                    )
                )
            )
        }

        assertThat(player.isReceivable()).isTrue

        player.finish()

        assertThat(player.isReceivable()).isFalse
    }
}
