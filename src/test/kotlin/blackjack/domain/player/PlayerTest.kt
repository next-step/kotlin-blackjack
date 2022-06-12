package blackjack.domain.player

import blackjack.domain.card.Card.AceCard
import blackjack.domain.card.Card.BasicCard
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardSuit
import blackjack.domain.card.ReceivedCards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `딜러가 추가되었는지 테스트`() {
        val cardDeck = CardDeck()
        val dealer = Dealer(cardDeck)

        assertThat(dealer.name).isEqualTo("딜러")
    }

    @Test
    fun `게임을 더 할 수 있는(기본(10)=10) 경우에 대한 테스트`() {
        val receivedCards = ReceivedCards(
            mutableSetOf(
                BasicCard(cardSuit = CardSuit.CLUB, number = 10)
            )
        )
        val player = Player(
            _name = "name",
            _receivedCards = receivedCards
        )


        assertThat(player.score).isEqualTo(10)
        assertThat(player.canMoreGame()).isTrue
    }

    @Test
    fun `게임을 더 할 수 없는(기본(10)+기본(9)+기본(8)=27) 경우에 대한 테스트`() {
        val receivedCards = ReceivedCards(
            mutableSetOf(
                BasicCard(cardSuit = CardSuit.CLUB, number = 10),
                BasicCard(cardSuit = CardSuit.CLUB, number = 9),
                BasicCard(cardSuit = CardSuit.CLUB, number = 8)
            )
        )
        val player = Player(
            _name = "name",
            _receivedCards = receivedCards
        )

        assertThat(player.score).isEqualTo(27)
        assertThat(player.canMoreGame()).isFalse
    }

    @Test
    fun `게임을 더 할 수 있는 (에이스(1)+에이스(1)=2) 경우에 대한 테스트`() {
        val receivedCards = ReceivedCards(
            mutableSetOf(
                AceCard(cardSuit = CardSuit.CLUB),
                AceCard(cardSuit = CardSuit.SPADE)
            )
        )
        val player = Player(
            _name = "name",
            _receivedCards = receivedCards
        )

        assertThat(player.score).isEqualTo(2)
        assertThat(player.canMoreGame()).isTrue
    }
    @Test
    fun `게임을 더 할 수 있는 (기본(10)+기본(7)+에이스(1)+에이스(1)=19) 경우에 대한 테스트`() {
        val receivedCards = ReceivedCards(
            mutableSetOf(
                BasicCard(cardSuit = CardSuit.CLUB, number = 10),
                BasicCard(cardSuit = CardSuit.CLUB, number = 7),
                AceCard(cardSuit = CardSuit.CLUB),
                AceCard(cardSuit = CardSuit.SPADE)
            )
        )
        val player = Player(
            _name = "name",
            _receivedCards = receivedCards
        )

        assertThat(player.score).isEqualTo(19)
        assertThat(player.canMoreGame()).isTrue
    }

    @Test
    fun `게임을 더 할 수 없는 (기본(10)+기본(8)+에이스(1)+에이스(1)+에이스(1)+에이스(1)=22) 경우에 대한 테스트`() {
        val receivedCards = ReceivedCards(
            mutableSetOf(
                BasicCard(cardSuit = CardSuit.CLUB, number = 10),
                BasicCard(cardSuit = CardSuit.CLUB, number = 8),
                AceCard(cardSuit = CardSuit.CLUB),
                AceCard(cardSuit = CardSuit.SPADE),
                AceCard(cardSuit = CardSuit.DIAMOND),
                AceCard(cardSuit = CardSuit.HEART)
            )
        )
        val player = Player(
            _name = "name",
            _receivedCards = receivedCards
        )

        assertThat(player.score).isEqualTo(22)
        assertThat(player.canMoreGame()).isFalse
    }

    @Test
    fun `게임을 더 할 수 있는 (기본(10)+에이스(1)+에이스(1)=12) 경우에 대한 테스트`() {
        val receivedCards = ReceivedCards(
            mutableSetOf(
                BasicCard(cardSuit = CardSuit.CLUB, number = 10),
                AceCard(cardSuit = CardSuit.CLUB),
                AceCard(cardSuit = CardSuit.SPADE)
            )
        )
        val player = Player(
            _name = "name",
            _receivedCards = receivedCards
        )

        assertThat(player.score).isEqualTo(12)
        assertThat(player.canMoreGame()).isTrue
    }

    @Test
    fun `게임을 더 할 수 없는 (기본(10)+에이스(11)=21) 경우에 대한 테스트`() {
        val receivedCards = ReceivedCards(
            mutableSetOf(
                BasicCard(cardSuit = CardSuit.CLUB, number = 10),
                AceCard(cardSuit = CardSuit.SPADE)
            )
        )
        val player = Player(
            _name = "name",
            _receivedCards = receivedCards
        )

        assertThat(player.score).isEqualTo(21)
        assertThat(player.canMoreGame()).isFalse
    }
}
