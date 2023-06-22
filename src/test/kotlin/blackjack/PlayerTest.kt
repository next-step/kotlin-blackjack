package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardType
import blackjack.domain.card.Cards
import blackjack.domain.card.Denomination
import blackjack.domain.player.Player
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `'카드 리스트'와 '카드 총합 점수'와 '이름' 정보를 가지고 있다`() {
        val player = Player()

        player.cards.value shouldNotBe null
        player.getOptimizedScore() shouldNotBe null
        player.name shouldNotBe null
    }

    @Test
    fun `게임 시작시 랜덤한 2장의 카드를 가지고 시작합니다`() {
        val deck = CardDeck()
        val cards = Cards(deck.getRandomCards(2).value)
        val player = Player()

        cards.value.forEach { card -> player.receiveCard(card) }

        player.cards.value.size shouldBe 2
    }

    @Test
    fun `최초 카드를 받은 후 BLACK_JACK 이면 더이상 카드를 받을 수 없다`() {
        val player = Player()
        val cards = Cards(
            mutableListOf(
                Card(Denomination.ACE, CardType.CLUBS),
                Card(Denomination.JACK, CardType.DIAMONDS),
            ),
        )
        cards.value.forEach { card -> player.receiveCard(card) }

        player.getOptimizedScore() shouldBe 21
        player.isBlackJack() shouldBe true
        player.isReceivable() shouldBe false
    }

    @Test
    fun `턴에 카드를 받은 후 BLACK_JACK score를 넘으면 더이상 카드를 받을 수 없다`() {
        val player = Player()
        val cards = Cards(
            mutableListOf(
                Card(Denomination.JACK, CardType.CLUBS),
                Card(Denomination.JACK, CardType.DIAMONDS),
            ),
        )
        cards.value.forEach { card -> player.receiveCard(card) }

        player.receiveCard(Card(Denomination.TWO, CardType.DIAMONDS))

        player.isReceivable() shouldBe false
    }
}
