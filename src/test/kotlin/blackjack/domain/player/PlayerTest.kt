package blackjack.domain.player

import blackjack.domain.card.Card.AceCard
import blackjack.domain.card.Card.BasicCard
import blackjack.domain.card.CardSuit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `딜러가 추가되었는지 테스트`() {
        val players = Players(listOf("플레이어"))

        assertThat(players.players.filterIsInstance<Dealer>().first().name).isEqualTo("딜러")
    }

    @Test
    fun `게임을 더 할 수 있는(기본(10)=10) 경우에 대한 테스트`() {
        val player = Player(
            "name",
            mutableSetOf(
                BasicCard(cardSuit = CardSuit.CLUB, number = 10)
            )
        )

        assertThat(player.calculateScore()).isEqualTo(10)
        assertThat(player.canMoreGame()).isTrue
    }

    @Test
    fun `게임을 더 할 수 없는(기본(10)+기본(9)+기본(8)=27) 경우에 대한 테스트`() {
        val player = Player(
            "name",
            mutableSetOf(
                BasicCard(cardSuit = CardSuit.CLUB, number = 10),
                BasicCard(cardSuit = CardSuit.CLUB, number = 9),
                BasicCard(cardSuit = CardSuit.CLUB, number = 8)
            )
        )

        assertThat(player.calculateScore()).isEqualTo(27)
        assertThat(player.canMoreGame()).isFalse
    }

    @Test
    fun `게임을 더 할 수 있는 (에이스(1)+에이스(1)=2) 경우에 대한 테스트`() {
        val player = Player(
            "name",
            mutableSetOf(
                AceCard(cardSuit = CardSuit.CLUB, number = 1),
                AceCard(cardSuit = CardSuit.SPADE, number = 1)
            )
        )

        assertThat(player.calculateScore()).isEqualTo(2)
        assertThat(player.canMoreGame()).isTrue
    }

    @Test
    fun `게임을 더 할 수 있는 (기본(10)+에이스(1)+에이스(1)=12) 경우에 대한 테스트`() {
        val player = Player(
            "name",
            mutableSetOf(
                BasicCard(cardSuit = CardSuit.CLUB, number = 10),
                AceCard(cardSuit = CardSuit.CLUB, number = 11),
                AceCard(cardSuit = CardSuit.SPADE, number = 11)
            )
        )

        assertThat(player.calculateScore()).isEqualTo(12)
        assertThat(player.canMoreGame()).isTrue
    }

    @Test
    fun `게임을 더 할 수 없는 (기본(10)+에이스(11)=21) 경우에 대한 테스트`() {
        val player = Player(
            "name",
            mutableSetOf(
                BasicCard(cardSuit = CardSuit.CLUB, number= 10),
                AceCard(cardSuit = CardSuit.SPADE, number = 11)
            )
        )

        assertThat(player.calculateScore()).isEqualTo(21)
        assertThat(player.canMoreGame()).isFalse
    }
}
