package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardType
import blackjack.domain.card.Cards
import blackjack.domain.card.Denomination
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerStatus
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `게임 시작시 랜덤한 2장의 카드를 가지고 시작하며 카드를 받을 수 있는 상태가 된다`() {
        val deck = CardDeck()
        val cards = deck.getRandomCards(2)
        val player = Player()

        player.initCards(cards)

        player.cards.getValue().size shouldBe 2
        player.getPlayerStatus() shouldBe PlayerStatus.RECEIVABLE
    }

    @Test
    fun `최초 카드를 받은 후 BLACK_JACK 이어도 카드를 받을 수 있다`() {
        val player = Player()
        val cards = Cards.from(
            Card(Denomination.ACE, CardType.CLUBS),
            Card(Denomination.JACK, CardType.DIAMONDS),
        )

        player.initCards(cards)

        player.cards.getOptimizedScore() shouldBe 21
        player.getPlayerStatus() shouldBe PlayerStatus.BLACK_JACK
        player.isReceivable() shouldBe true
    }

    @Test
    fun `턴에 카드를 받은 후 BLACK_JACK score를 넘으면 더이상 카드를 받을 수 없다`() {
        val player = Player()
        val cards = Cards.from(
            Card(Denomination.JACK, CardType.CLUBS),
            Card(Denomination.JACK, CardType.DIAMONDS),
        )

        player.initCards(cards)
        player.receiveCard(Card(Denomination.TWO, CardType.DIAMONDS))

        player.getPlayerStatus() shouldBe PlayerStatus.BUST
        player.isReceivable() shouldBe false
    }
}
