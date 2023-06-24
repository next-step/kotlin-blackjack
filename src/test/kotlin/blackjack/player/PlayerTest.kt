package blackjack.player

import domain.card.Card
import domain.card.CardNumber
import domain.card.Suit
import domain.player.Dealer
import domain.player.Player
import domain.player.PlayerGameResult
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

    @Test
    fun `딜러는 카드 합계가 21을 초과하면 살아있는 플레이어는 무조건 승리`() {
        val player = Player(
            name = "남상윤",
            card1 = Card(suit = Suit.SPADE, number = CardNumber.TWO),
            card2 = Card(suit = Suit.CLUB, number = CardNumber.ACE),
        )

        val dealer = Dealer(
            card1 = Card(suit = Suit.SPADE, number = CardNumber.FIVE),
            card2 = Card(suit = Suit.CLUB, number = CardNumber.JACK),
        )

        val newCard = Card(suit = Suit.SPADE, number = CardNumber.SEVEN)
        player.draw(newCard)
        dealer.draw(newCard)

        player.getPlayerGameResult(dealer) shouldBe PlayerGameResult.WIN
    }

    @Test
    fun `딜러는 카드 합계가 21이하고 살아있는 플레이어 카드 합계 보다 크다면 딜러가 승리`() {
        val player = Player(
            name = "남상윤",
            card1 = Card(suit = Suit.SPADE, number = CardNumber.TWO),
            card2 = Card(suit = Suit.CLUB, number = CardNumber.ACE),
        )

        val dealer = Dealer(
            card1 = Card(suit = Suit.SPADE, number = CardNumber.FIVE),
            card2 = Card(suit = Suit.CLUB, number = CardNumber.NINE),
        )

        val newCard = Card(suit = Suit.SPADE, number = CardNumber.SEVEN)
        player.draw(newCard)
        dealer.draw(newCard)

        player.getPlayerGameResult(dealer) shouldBe PlayerGameResult.LOSE
    }

    @Test
    fun `딜러는 카드 합계가 21이하고 살아있는 플레이어 카드 합계 보다 작다면 딜러가 패한다`() {
        val player = Player(
            name = "남상윤",
            card1 = Card(suit = Suit.SPADE, number = CardNumber.TWO),
            card2 = Card(suit = Suit.CLUB, number = CardNumber.ACE),
        )

        val dealer = Dealer(
            card1 = Card(suit = Suit.SPADE, number = CardNumber.FIVE),
            card2 = Card(suit = Suit.CLUB, number = CardNumber.FIVE),
        )

        val newCard = Card(suit = Suit.SPADE, number = CardNumber.SEVEN)
        player.draw(newCard)
        dealer.draw(newCard)

        player.getPlayerGameResult(dealer) shouldBe PlayerGameResult.WIN
    }

    @Test
    fun `딜러는 카드 합계가 21이하고 살아있는 플레이어 카드 합계와 같다면 딜러가 무승부다`() {
        val player = Player(
            name = "남상윤",
            card1 = Card(suit = Suit.SPADE, number = CardNumber.TWO),
            card2 = Card(suit = Suit.CLUB, number = CardNumber.ACE),
        )

        val dealer = Dealer(
            card1 = Card(suit = Suit.SPADE, number = CardNumber.THREE),
            card2 = Card(suit = Suit.CLUB, number = CardNumber.JACK),
        )

        val newCard = Card(suit = Suit.SPADE, number = CardNumber.SEVEN)
        player.draw(newCard)
        dealer.draw(newCard)

        player.getPlayerGameResult(dealer) shouldBe PlayerGameResult.DRAW
    }
}
