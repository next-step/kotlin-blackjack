@file:Suppress("NonAsciiCharacters")

package blackjack.domain

import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkObject
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    @BeforeEach
    fun setUp() {
        mockkObject(Dealer)
    }

    @AfterEach
    fun tearDown() {
        unmockkObject(Dealer)
    }

    @Test
    fun `플레이어는 카드를 받을 수 있다`() {
        val card = Card(Denomination.ACE, Suit.SPADE)
        val player = Player("a")

        player.receiveCard(card)

        assertThat(player.cards.get()).containsExactly(card)
    }

    @Test
    fun `플레이어가 가진 카드 숫자의 총합이 21 초과이면 더이상 카드를 받을 수 없다`() {
        val card1 = Card(Denomination.TEN, Suit.SPADE)
        val card2 = Card(Denomination.TEN, Suit.DIAMOND)
        val card3 = Card(Denomination.TWO, Suit.SPADE)
        val player = Player("a")

        player.run {
            receiveCard(card1)
            receiveCard(card2)
            receiveCard(card3)
        }

        val actual = player.isBust()

        assertThat(actual).isTrue()
    }

    @Test
    fun `플레이어가 가진 카드 숫자의 총합이 21 이하이면 카드를 더 받을 수 있다`() {
        val card1 = Card(Denomination.TEN, Suit.SPADE)
        val card2 = Card(Denomination.TEN, Suit.DIAMOND)
        val player = Player("a")

        player.run {
            receiveCard(card1)
            receiveCard(card2)
        }

        val actual = player.isBust()

        assertThat(actual).isFalse()
    }

    @Test
    fun `플레이어의 점수가 21을 초과하면 딜러의 점수와 상관없이 패배한다`() {
        val card1 = Card(Denomination.TEN, Suit.SPADE)
        val card2 = Card(Denomination.TEN, Suit.DIAMOND)
        val card3 = Card(Denomination.TWO, Suit.SPADE)
        val player = Player("a")

        player.run {
            receiveCard(card1)
            receiveCard(card2)
            receiveCard(card3)
        }

        val actual = player.getResult()

        assertThat(actual).isEqualTo(PlayerResult.LOSE)
    }

    @Test
    fun `딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관없이 승리한다`() {
        val card1 = Card(Denomination.TEN, Suit.SPADE)
        val card2 = Card(Denomination.TEN, Suit.DIAMOND)
        val player = Player("a")

        every { Dealer.cards.calculateScore() } returns 22

        player.run {
            receiveCard(card1)
            receiveCard(card2)
        }

        val actual = player.getResult()

        assertThat(actual).isEqualTo(PlayerResult.WIN)
    }

    @Test
    fun `플레이어의 점수가 딜러의 점수보다 높으면 플레이어가 승리한다`() {
        val card1 = Card(Denomination.TEN, Suit.SPADE)
        val card2 = Card(Denomination.TEN, Suit.DIAMOND)
        val player = Player("a")

        every { Dealer.cards.calculateScore() } returns 19

        player.run {
            receiveCard(card1)
            receiveCard(card2)
        }

        val actual = player.getResult()

        assertThat(actual).isEqualTo(PlayerResult.WIN)
    }

    @Test
    fun `플레이어의 점수가 딜러의 점수와 같으면 무승부이다`() {
        val card1 = Card(Denomination.TEN, Suit.SPADE)
        val card2 = Card(Denomination.TEN, Suit.DIAMOND)
        val player = Player("a")

        every { Dealer.cards.calculateScore() } returns 20

        player.run {
            receiveCard(card1)
            receiveCard(card2)
        }

        val actual = player.getResult()

        assertThat(actual).isEqualTo(PlayerResult.DRAW)
    }

    @Test
    fun `플레이어의 점수가 딜러의 점수보다 낮으면 플레이어가 패배한다`() {
        val card1 = Card(Denomination.TEN, Suit.SPADE)
        val card2 = Card(Denomination.TEN, Suit.DIAMOND)
        val player = Player("a")

        every { Dealer.cards.calculateScore() } returns 21

        player.run {
            receiveCard(card1)
            receiveCard(card2)
        }

        val actual = player.getResult()

        assertThat(actual).isEqualTo(PlayerResult.LOSE)
    }

    @Test
    fun `플레이어 승패에 따른 딜러의 승패 결과를 반환한다`() {
        val playerResults = listOf(
            PlayerResult.WIN,
            PlayerResult.LOSE,
            PlayerResult.WIN,
            PlayerResult.DRAW,
        )

        val actual = Dealer.getResult(playerResults)

        assertThat(actual).containsExactlyInAnyOrderEntriesOf(
            mapOf(
                PlayerResult.WIN to 1,
                PlayerResult.DRAW to 1,
                PlayerResult.LOSE to 2,
            )
        )
    }
}
