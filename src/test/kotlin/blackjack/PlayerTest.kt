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
    private lateinit var deck1: Deck
    private lateinit var deck2: Deck
    private lateinit var deck3: Deck
    private lateinit var deck4: Deck

    @BeforeEach
    fun `set up`() {
        player = Player(name = "mark")
        deck1 = Deck(setOf(Card(Pair(Suit.HEART, Denomination.FOUR))))
        deck2 = Deck(setOf(Card(Pair(Suit.SPADE, Denomination.KING))))
        deck3 = Deck(setOf(Card(Pair(Suit.DIAMOND, Denomination.JACK))))
        deck4 = Deck(setOf(Card(Pair(Suit.CLUB, Denomination.QUEEN))))
    }

    @Test
    fun `이름 출력`() {
        assertThat(player.toString()).isEqualTo("mark")
    }

    @Test
    fun `드로우`() {

        val cards = player.draw(deck1)
        val nullCards = player.draw(deck1)

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
        player.draw(deck1)

        val amount = player.amountOfCards()

        assertThat(amount).isEqualTo(1)
    }

    @Test
    fun `스코어 총 점수`() {
        player.draw(deck1)

        val totalScore = player.amountOfScores()

        assertThat(totalScore).isEqualTo(4)
    }

    @Test
    fun `드로우 선택`() {

        player.chooseToDraw("y", deck2)
        player.chooseToDraw("y", deck1)
        player.chooseToDraw("y", deck3)
        player.chooseToDraw("y", deck4)
        assertThat(player.amountOfCards()).isEqualTo(3)
    }
}
