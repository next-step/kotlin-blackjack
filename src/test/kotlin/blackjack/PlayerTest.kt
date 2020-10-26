package blackjack

import blackjack.domain.Card
import blackjack.domain.Deck
import blackjack.domain.Denomination
import blackjack.domain.Player
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player
    private lateinit var deck: Deck

    @BeforeEach
    fun `set up`() {
        player = Player(name = "mark")
        deck = Deck(setOf(Card(Pair(Suit.HEART, Denomination.FOUR))))
    }

    @Test
    fun `이름 출력`() {
        assertThat(player.toString()).isEqualTo("mark")
    }

    @Test
    fun `드로우`() {

        val cards = player.draw(deck)
        val nullCards = player.draw(deck)

        assertThat(cards?.size()).isEqualTo(1)
        assertThat(nullCards?.size()).isEqualTo(null)
    }

    @Test
    fun `맥시멈 스코어 보다 클 경우`() {
        val isMoreThanMax = player.hasScoreMoreThanMax()

        assertFalse(isMoreThanMax)
    }

    @Test
    fun `카드의 양`() {
        player.draw(deck)

        val amount = player.amountOfCards()

        assertThat(amount).isEqualTo(1)
    }

    @Test
    fun `스코어 총 점수`() {
        player.draw(deck)

        val totalScore = player.amountOfScores()

        assertThat(totalScore).isEqualTo(4)
    }
}
