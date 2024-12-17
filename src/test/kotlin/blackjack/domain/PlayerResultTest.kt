package blackjack.domain

import blackjack.domain.enums.Card
import blackjack.domain.enums.CardSymbol
import blackjack.entity.CardInfo
import blackjack.entity.Player
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlayerResultTest {
    @Test
    fun `플레이어가 버스트`() {
        val player = Player(
            name = "문장호",
            betAmount = 100,
            initialCards = listOf(
                CardInfo(
                    mapOf(CardSymbol.HEART to Card.KING),
                    true
                ),
                CardInfo(
                    mapOf(CardSymbol.DIAMOND to Card.NINE),
                    true
                ),

                CardInfo(
                    mapOf(CardSymbol.SPADE to Card.FIVE),
                    true
                )

            )
        )

        val result = PlayerResult.from(
            player = player,
            isDealerBlackJack = false,
            dealerScore = 18,
            bustLimit = 21
        )

        assertEquals(-100, result.playerProfitAmount)
    }

    @Test
    fun `플레이어가 첫 번째 카드 블랙잭`() {
        val player = Player(
            name = "문장호",
            betAmount = 200,
            initialCards = listOf(
                CardInfo(
                    mapOf(CardSymbol.HEART to Card.KING),
                    true
                ),
                CardInfo(
                    mapOf(CardSymbol.DIAMOND to Card.A),
                    true
                )

            )
        )

        val result = PlayerResult.from(
            player = player,
            isDealerBlackJack = false,
            dealerScore = 20,
            bustLimit = 21
        )
        assertEquals((200 * 1.5).toInt(), result.playerProfitAmount)
    }

    @Test
    fun `플레이어와 딜러 모두 블랙잭`() {
        val player = Player(
            name = "문장호",
            betAmount = 150,
            initialCards = listOf(
                CardInfo(
                    mapOf(CardSymbol.HEART to Card.KING),
                    true
                ),

                CardInfo(
                    mapOf(CardSymbol.DIAMOND to Card.JACK),
                    true
                ),
                        CardInfo(
                        mapOf(CardSymbol.DIAMOND to Card.A),
                true
            )
            )
        )

        val result = PlayerResult.from(
            player = player,
            isDealerBlackJack = true,
            dealerScore = 21,
            bustLimit = 21
        )

        assertEquals(150, result.playerProfitAmount)
    }

    @Test
    fun `플레이어 점수가 딜러보다 높다`() {
        val player = Player(
            name = "문",
            betAmount = 300,
            initialCards = listOf(
                CardInfo(
                    mapOf(CardSymbol.HEART to Card.KING),
                    true
                ),
                CardInfo(
                    mapOf(CardSymbol.DIAMOND to Card.NINE),
                    true
                )
            )
        )

        val result = PlayerResult.from(
            player = player,
            isDealerBlackJack = false,
            dealerScore = 18,
            bustLimit = 21
        )

        assertEquals(300, result.playerProfitAmount)
    }

    @Test
    fun `플레이어와 딜러 점수가 같다`() {
        val player = Player(
            name = "문장호",
            betAmount = 400,
            initialCards = listOf(
                CardInfo(
                    mapOf(CardSymbol.HEART to Card.KING),
                    true
                ),
                CardInfo(
                    mapOf(CardSymbol.DIAMOND to Card.NINE),
                    true
                )
            )
        )

        val result = PlayerResult.from(
            player = player,
            isDealerBlackJack = false,
            dealerScore = 19,
            bustLimit = 21
        )
        assertEquals(0, result.playerProfitAmount)
    }

    @Test
    fun `플레이어 점수가 딜러보다 낮다`() {
        val player = Player(
            name = "문",
            betAmount = 500,
            initialCards = listOf(
                CardInfo(
                    mapOf(CardSymbol.HEART to Card.SEVEN),
                    true
                ),
                CardInfo(
                    mapOf(CardSymbol.DIAMOND to Card.EIGHT),
                    true
                )
            )
        )

        val result = PlayerResult.from(
            player = player,
            isDealerBlackJack = false,
            dealerScore = 18,
            bustLimit = 21
        )

        assertEquals(-500, result.playerProfitAmount)
    }
}
