package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.card.Card
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class DealerTest {
    private lateinit var dealer: Dealer

    @BeforeEach
    fun beforeEach() {
        dealer = Dealer()
    }

    @DisplayName("딜러는 CardDeck을 가지고 있다.")
    @Test
    fun dealerHasCardDeck() {
        dealer.cardDeck.shouldNotBeNull()
    }

    @DisplayName("딜러는 랜덤한 카드를 뽑을 수 있다")
    @Test
    fun drawRandomCard() {
        val actual = dealer.draw()

        actual.shouldBeTypeOf<Card>()
    }

    @DisplayName("딜러가 뽑은 카드는 덱에서 사라진다.")
    @Test
    fun drawCardNotInDeck() {
        val beforeDraw = dealer.cardDeck.size()
        dealer.draw()
        val afterDraw = dealer.cardDeck.size()

        afterDraw shouldBe beforeDraw - 1
    }

    @DisplayName("딜러는 플레이어에게 카드를 줄 수 있다.")
    @Test
    fun deal() {
        val player = Player()
        dealer.deal(player)

        val actual = player.cards

        actual.size shouldBe 1
    }
}
