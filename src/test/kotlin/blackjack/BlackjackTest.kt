package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.CardDeck
import blackjack.domain.CardType
import blackjack.domain.NumberType
import blackjack.domain.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BlackjackTest {
    @Test
    fun `카드의 합이 21이 되면 블랙잭이다`() {
        val name = "홍길동"
        val card = Cards(
            mutableListOf(
                Card(NumberType.ACE, CardType.DIAMOND),
                Card(NumberType.KING, CardType.SPADE),
                Card(NumberType.QUEEN, CardType.SPADE)
            )
        )
        val player = Player(name = name)
        card.card.forEach { player.receiveCard(it) }

        player.score.first() shouldBe 21
        player.isBlackJack() shouldBe true
    }

    @Test
    fun `블랙잭을 넘어가는 점수에는 카드를 받을 수 없다`() {
        val name = "홍길동"
        val card = Cards(
            mutableListOf(
                Card(NumberType.QUEEN, CardType.DIAMOND),
                Card(NumberType.KING, CardType.SPADE)
            )
        )
        val player = Player(name = name)
        card.card.forEach { player.receiveCard(it) }

        val receiveCard = Card(NumberType.NINE, CardType.HEART)
        player.receiveCard(receiveCard)

        player.isGetCardPossible() shouldBe false
    }
}
