package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerTest {
    @Test
    fun `Player는 이름과 손패, 점수를 보관한다`() {
        val player = Player(
            name = PlayerName("이름"),
            initialCards = PlayingCards.empty()
        )

        assertAll(
            { assertThat(player.name.value).isEqualTo("이름") },
            { assertThat(player.cardsOfHands).isEmpty() },
            { assertThat(player.score).isEqualTo(Score.zero()) }
        )
    }

    @Test
    fun `receive를 통해 카드를 받아 손패에 추가할 수 있다`() {
        val player = Player(
            name = PlayerName("이름"),
            initialCards = PlayingCards.empty()
        )
        player.receive(
            PlayingCards.from(
                listOf(
                    PlayingCard(Suit.CLUBS, CardNumber.JACK),
                    PlayingCard(Suit.CLUBS, CardNumber.ACE)
                )
            )
        )

        assertAll(
            { assertThat(player.cardsOfHands).hasSize(2) }
        )
    }

    @Test
    fun `stay를 통해 더 이상 카드를 받지 않겠다는 의사를 나타낼 수 있다`() {
        val player = Player(
            name = PlayerName("이름"),
            initialCards = PlayingCards.from(
                listOf(
                    PlayingCard(Suit.CLUBS, CardNumber.JACK),
                    PlayingCard(Suit.CLUBS, CardNumber.KING)
                )
            )
        )
        player.stay()

        assertThat(player.isReceivable()).isFalse
    }

    @Test
    fun `isReceivable을 통해 플레이어가 카드를 받을 수 있는 상태인지 알 수 있다`() {
        val receivablePlayer = Player(
            name = PlayerName("이름"),
            initialCards = PlayingCards.from(
                listOf(
                    PlayingCard(Suit.CLUBS, CardNumber.JACK),
                    PlayingCard(Suit.CLUBS, CardNumber.KING)
                )
            )
        )
        assertThat(receivablePlayer.isReceivable()).isTrue

        val nonReceivablePlayer = Player(
            name = PlayerName("이름"),
            initialCards = PlayingCards.from(
                listOf(
                    PlayingCard(Suit.CLUBS, CardNumber.JACK),
                    PlayingCard(Suit.CLUBS, CardNumber.ACE)
                )
            )
        )
        assertThat(nonReceivablePlayer.isReceivable()).isFalse
    }
}
