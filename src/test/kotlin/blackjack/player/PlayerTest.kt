package blackjack.player

import domain.card.Card
import domain.card.CardNumber
import domain.card.Suit
import domain.player.Player
import domain.state.Hit
import domain.state.ProceedingState
import domain.state.Stand
import domain.state.TerminationState
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `플레이어는 카드를 뽑을 수 있다`() {
        val player = Player(
            name = "남상윤",
            card1 = Card(suit = Suit.SPADE, number = CardNumber.TWO),
            card2 = Card(suit = Suit.CLUB, number = CardNumber.ACE),
        )
        val newCard = Card(suit = Suit.HEART, number = CardNumber.TEN)

        val playerState = player.draw(newCard)

        (playerState is Hit) shouldBe true
        (playerState is ProceedingState) shouldBe true
    }

    @Test
    fun `플레이어는 카드를 안 받을 수 있다`() {
        val player = Player(
            name = "남상윤",
            card1 = Card(suit = Suit.SPADE, number = CardNumber.TWO),
            card2 = Card(suit = Suit.CLUB, number = CardNumber.ACE),
        )

        val playerState = player.stop()

        (playerState is Stand) shouldBe true
        (playerState is TerminationState) shouldBe true
    }
}
