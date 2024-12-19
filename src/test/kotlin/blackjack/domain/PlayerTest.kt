package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class PlayerTest {
    @Test
    @DisplayName("Player 가 계속해서 Rank SEVEN 만 뽑으면, 최대 3번 뽑을 수 있고 카드 합은 21이다")
    fun `player can draw up to 3 cards if all are Rank SEVEN, with a total sum of 21`() {
        val fakeDeck = Deck { Card(Rank.SEVEN, Suit.HEARTS) }
        val player =
            Player(
                name = "sara",
                betMoney = BetMoney(BigDecimal.ZERO),
                drawCard = { fakeDeck.draw() },
            )
        player.play(
            isDrawCard = { true },
            onDrawCard = {},
            onExitPlay = {},
        )
        assertThat(player.cards.value.size).isEqualTo(3)
        assertThat(player.cardsSum).isEqualTo(21)
    }

    @Test
    @DisplayName("Player 가 카드를 뽑지 않는다면 기본으로 주어진 카드만 갖는다")
    fun `player keeps the default cards if no additional cards are drawn`() {
        val fakeDeck = Deck { Card(Rank.TWO, Suit.HEARTS) } // 기본으로 주어질 카드
        val player =
            Player(
                name = "sara",
                betMoney = BetMoney(BigDecimal.ZERO),
                drawCard = { fakeDeck.draw() },
            )
        player.play(
            // 카드를 추가로 뽑지 않는다
            isDrawCard = { false },
            onDrawCard = {},
            onExitPlay = {},
        )
        assertThat(player.cards.value.size).isEqualTo(2)
        assertThat(player.cardsSum).isEqualTo(4)
    }

    @Test
    @DisplayName("BUST 되었다면 플레이어의 베팅 금액을 모두 잃는다")
    fun `player loses all money on bust`() {
        // Given
        val player = initFakePlayer()

        // When
        val profit = player.getProfitMoney(GameResult.BUST)

        // Then
        assertEquals(BigDecimal(-10000), profit)
    }

    @Test
    @DisplayName("WIN 이면 플레이어의 베팅 금액을 그대로 가져간다")
    fun `player wins and keeps the bet amount`() {
        // Given
        val player = initFakePlayer()

        // When
        val profit = player.getProfitMoney(GameResult.WIN)

        // Then
        assertEquals(BigDecimal(10000), profit)
    }

    @Test
    @DisplayName("LOSE 이면 플레이어의 베팅 금액을 모두 잃는다")
    fun `player loses all money on lose`() {
        // Given
        val player = initFakePlayer()

        // When
        val profit = player.getProfitMoney(GameResult.LOSE)

        // Then
        assertEquals(BigDecimal(-10000), profit)
    }

    @Test
    @DisplayName("PUSH 이면 플레이어의 베팅 금액은 변동되지 않는다")
    fun `player's profit remains the same on push`() {
        // Given
        val player = initFakePlayer()

        // When
        val profit = player.getProfitMoney(GameResult.PUSH)

        // Then
        assertEquals(BigDecimal(10000), profit)
    }

    @Test
    @DisplayName("최초 할당된 2장의 카드 합이 21 이라면 블랙잭이다")
    fun `if player's initial two cards sum is 21, it is blackjack`() {
        val player = initBlackJackPlayer()
        assertThat(player.cardsSum == Card.MAX_SUM).isTrue()
        assertThat(player.isBlackJackInitially).isTrue()
    }

    @Test
    @DisplayName("플레이어에 할당된 최초 2장이 블랙잭이면, 베팅금액의 1.5배를 받는다")
    fun `player's profit multiplies by 150 percent on blackjack`() {
        // Given
        val player = initBlackJackPlayer()

        // When
        val profit = player.getProfitMoney(GameResult.BLACK_JACK)

        // Then
        assertEquals(BigDecimal(15000), profit)
    }

    private fun initFakePlayer(): Player {
        val betAmount = BetMoney(BigDecimal(10000))
        val fakeDeck = Deck { Card(Rank.KING, Suit.HEARTS) }
        val player =
            Player(
                name = "Pobi",
                betMoney = betAmount,
                drawCard = { fakeDeck.draw() },
            )
        return player
    }

    private fun initBlackJackPlayer(): Player {
        val betAmount = BetMoney(BigDecimal(10000))
        val cards =
            ArrayDeque(
                listOf(
                    Card(Rank.ACE, Suit.HEARTS),
                    Card(Rank.KING, Suit.HEARTS),
                ),
            )
        val fakeDeck = Deck { cards.removeFirst() }
        val player =
            Player(
                name = "Pobi",
                betMoney = betAmount,
                drawCard = { fakeDeck.draw() },
            )
        return player
    }
}
