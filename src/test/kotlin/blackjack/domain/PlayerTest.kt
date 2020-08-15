package blackjack.domain

import blackjack.view.REPLY_HIT
import blackjack.view.REPLY_STAND
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player
    private lateinit var deck: Deck
    private lateinit var dealer: Dealer

    @BeforeEach
    fun `set up`() {
        player = Player(name = "mark")
        deck = Deck(setOf(Card(Pair(CardScore.SEVEN, Suit.SPADE))))
        dealer = Dealer(deck)
    }

    @DisplayName("카드를 뽑을지 말지는 reply에 따라 다르다 (reply : HIT = 1장 받음, STAND = 안 받음)")
    @Test
    fun `choose to draw or not`() {
        // when
        player.chooseToDraw(REPLY_HIT, dealer)
        player.chooseToDraw(REPLY_STAND, dealer)

        // then
        assertThat(player.amountOfCards()).isEqualTo(1)
    }

    @DisplayName("카드를 뽑을 때 1장씩 반환되고, 덱이 비어있으면 null이 반환된다")
    @Test
    fun `draw a card`() {
        // when
        val cards = player.draw(dealer.pickCard())
        val nullCards = player.draw(dealer.pickCard())

        // then
        assertThat(cards?.size()).isEqualTo(1)
        assertThat(nullCards).isEqualTo(null)
    }

    @Test
    fun `has score more than maximum score`() {
        // when
        val isMoreThanMax = player.hasMoreScoreThanMax(player.totalScore())

        // then
        assertFalse(isMoreThanMax)
    }

    @Test
    fun `amount of cards`() {
        // given
        player.draw(dealer.pickCard())

        // when
        val amount = player.amountOfCards()

        // then
        assertThat(amount).isEqualTo(1)
    }

    @Test
    fun `sum of scores`() {
        // given
        player.draw(dealer.pickCard())

        // when
        val sum = player.totalScore()

        // then
        assertThat(sum).isEqualTo(7)
    }
}
