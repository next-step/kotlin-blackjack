package blackjack.domain

import blackjack.ScoreCalculator
import blackjack.card.CardDeck
import blackjack.card.CardPattern
import blackjack.card.CardPicture
import blackjack.card.NormalCard
import blackjack.card.PictureCard
import blackjack.participant.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    private val scoreCalculator: ScoreCalculator = ScoreCalculator()

    @Test
    fun `플레이어는 이름을 가질 수 있다`() {
        val name = "홍길동"
        val player = Player(name, scoreCalculator)

        player.name shouldBe name
    }

    @Test
    fun `플레이어는 카드를 뽑을 수 있다`() {
        val name = "홍길동"
        val player = Player(name, scoreCalculator)
        val cardDeck = CardDeck

        player.drawCard(cardDeck.draw(2))
        player.cards.size shouldBe 2
    }

    @Test
    fun `플레이어는 카드 점수 합계가 21미만이면 카드를 뽑을 수 있다`() {
        val name = "홍길동"
        val player = Player(name, scoreCalculator)
        player.drawCard(
            listOf(
                NormalCard(9, CardPattern.CLOVER),
            )
        )

        player.shouldDraw() shouldBe true
    }

    @Test
    fun `플레이어는 카드 점수 합계가 21이상이면 카드를 뽑을 수 없다`() {
        val name = "홍길동"
        val player = Player(name, scoreCalculator)
        player.drawCard(
            listOf(
                NormalCard(9, CardPattern.CLOVER),
                PictureCard(CardPicture.KING, CardPattern.CLOVER),
                PictureCard(CardPicture.KING, CardPattern.SPADE)
            )
        )

        player.shouldDraw() shouldBe false
    }

    @Test
    fun `플레이어는 카드 점수 합계가 21이상이면 버스트 상태가 된다`() {
        val name = "홍길동"
        val player = Player(name, scoreCalculator)
        player.drawCard(
            listOf(
                NormalCard(9, CardPattern.CLOVER),
                PictureCard(CardPicture.KING, CardPattern.CLOVER),
                PictureCard(CardPicture.KING, CardPattern.SPADE)
            )
        )

        player.isBust shouldBe true
    }
}
