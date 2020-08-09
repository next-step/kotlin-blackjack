package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlackJackGameTest {
    @Test
    fun `플레이어 이름 리스트 반환 확인`() {
        // given
        val expectedNames = listOf("malibin", "jwaeng")
        val game = BlackJackGame(expectedNames, Deck())

        // then
        assertThat(game.playerNames).isEqualTo(expectedNames)
    }

    @Test
    fun `특정 플레이어의 카드들 가져오기`() {
        // given
        val playerName = "Malibin"
        val expectedCard = Card.denominationOf("5")
        val deck = object : DrawStrategy {
            override fun fetchCard(): Card {
                return expectedCard
            }
        }
        val game = BlackJackGame(listOf(playerName), deck)

        // then
        assertThat(game.playerCardsOf(playerName)).isEqualTo(listOf(expectedCard, expectedCard))
    }

    @Test
    fun `특정 플레이어의 점수 가져오기`() {
        // given
        val playerName = "Malibin"
        val expectedCard = Card.denominationOf("5")
        val deck = object : DrawStrategy {
            override fun fetchCard(): Card {
                return expectedCard
            }
        }
        val game = BlackJackGame(listOf(playerName), deck)

        // then
        assertThat(game.playerScoreOf(playerName)).isEqualTo(10)
    }
}

