package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardHold
import blackjack.domain.card.CardRank
import blackjack.domain.card.CardShape
import blackjack.domain.card.Deck
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 처음 포인트가 0이다`() {
        // given
        val dealer = Dealer()
        // when
        dealer.getPoints() shouldBe 0
    }

    @Test
    fun `딜러는 카드를 뽑을 수 있다`() {
        // given
        val dealer = Dealer()
        val deck = Deck()
        // when
        dealer.drawCard(deck)
        // then
        dealer.cardHold.getCardsTotalSize() shouldBe 1
        dealer.getPoints() shouldNotBe 0
    }

    @Test
    fun `포인트가 17 이상인 경우 카드를 뽑을 수 없다`() {
        val sampleCard = Card.createCard(CardRank.KING, CardShape.CLOVER)
        val sampleCard2 = Card.createCard(CardRank.SEVEN, CardShape.HEART)
        val myCards = CardHold(mutableListOf(sampleCard, sampleCard2))
        val dealer = Dealer(cardHold = myCards)

        // when
        val result = dealer.canDraw()

        // then
        result shouldBe false
    }

    @Test
    fun `21이 넘는 경우 ACE 카드는 포인트 1 취급을 받는다`() {
        val sampleCard = Card.createCard(CardRank.JACK, CardShape.CLOVER)
        val sampleCard2 = Card.createCard(CardRank.QUEEN, CardShape.HEART)
        val sampleCard3 = Card.createCard(CardRank.KING, CardShape.DIAMOND)
        val sampleCard4 = Card.createCard(CardRank.ACE, CardShape.HEART)
        val myCards = CardHold(mutableListOf(sampleCard, sampleCard2, sampleCard3, sampleCard4))
        val dealer = Dealer(cardHold = myCards)

        // when
        val result = dealer.getPoints()

        // then
        result shouldBe 31
    }

    @Test
    fun `플레이어는 돈을 베팅할 수 있다`() {
        // given
        val player = GamePlayer("player")
        val dealer = Dealer()
        dealer.getMoneyFromPlayer(player, 10000)

        // when
        val money = player.getBettingMoney()

        // then
        money.value shouldBe 10000
    }

    @Test
    fun `플레이어에게 돈을 돌려줄 수 있다`() {
        // given
        val player = GamePlayer("player")
        val dealer = Dealer()
        dealer.getMoneyFromPlayer(player, 10000)

        // when
        dealer.giveMoneyToPlayer(player)

        // then
        dealer.money.value shouldBe -10000
        player.money.value shouldBe 10000
    }

    @Test
    fun `플레이어에게 돈을 150프로 추가로 돌려준다`() {
        // given
        val player = GamePlayer("player")
        val dealer = Dealer()
        dealer.getMoneyFromPlayer(player, 10000)

        // when
        dealer.giveMoneyToPlayerInSpecial(player)

        // then
        dealer.money.value shouldBe -15000
        player.money.value shouldBe 15000
    }

    @Test
    fun `플레이어가 21점을 넘긴경우 무조건 패한다`() {
        // given
        val player = GamePlayer("player")
        val dealer = Dealer()
        dealer.getMoneyFromPlayer(player, 10000)

        // when
        dealer.giveMoneyToPlayerInSpecial(player)

        // then
        dealer.money.value shouldBe -15000
        player.money.value shouldBe 15000
    }

    @Test
    fun `플레이어는 21점 미만이면서 딜러가 21점을 넘긴 경우 딜러는 무조건 패한다`() {
        // given
        val sampleCard = Card.createCard(CardRank.JACK, CardShape.CLOVER)
        val myCards = CardHold(mutableListOf(sampleCard))
        val gamePlayer = GamePlayer("goofy", myCards)

        val sampleCard2 = Card.createCard(CardRank.QUEEN, CardShape.HEART)
        val sampleCard3 = Card.createCard(CardRank.JACK, CardShape.HEART)
        val sampleCard4 = Card.createCard(CardRank.QUEEN, CardShape.CLOVER)
        val dealerCards = CardHold(mutableListOf(sampleCard2, sampleCard3, sampleCard4))
        val dealer = Dealer(cardHold = dealerCards)

        // when
        dealer.getMoneyFromPlayer(gamePlayer, 10000)
        dealer.compareScore(gamePlayer)

        // then
        dealer.money.value shouldBe -10000
        gamePlayer.money.value shouldBe 10000
    }

    @Test
    fun `플레이어와 딜러가 동시에 블랙잭인 경우 비긴다`() {
        // given
        val sampleCard = Card.createCard(CardRank.JACK, CardShape.CLOVER)
        val sampleCard2 = Card.createCard(CardRank.ACE, CardShape.CLOVER)
        val myCards = CardHold(mutableListOf(sampleCard, sampleCard2))
        val gamePlayer = GamePlayer("goofy", myCards)

        val sampleCard3 = Card.createCard(CardRank.JACK, CardShape.HEART)
        val sampleCard4 = Card.createCard(CardRank.ACE, CardShape.HEART)
        val dealerCards = CardHold(mutableListOf(sampleCard3, sampleCard4))
        val dealer = Dealer(cardHold = dealerCards)

        // when
        dealer.getMoneyFromPlayer(gamePlayer, 10000)
        dealer.compareScore(gamePlayer)

        // then
        dealer.money.value shouldBe 0
        gamePlayer.money.value shouldBe 0
    }

    @Test
    fun `플레이어만 블랙잭인 경우 150프로의 금액을 추가로 돌려준다`() {
        // given
        val sampleCard = Card.createCard(CardRank.JACK, CardShape.CLOVER)
        val sampleCard2 = Card.createCard(CardRank.ACE, CardShape.CLOVER)
        val myCards = CardHold(mutableListOf(sampleCard, sampleCard2))
        val gamePlayer = GamePlayer("goofy", myCards)

        val sampleCard3 = Card.createCard(CardRank.JACK, CardShape.HEART)
        val dealerCards = CardHold(mutableListOf(sampleCard3))
        val dealer = Dealer(cardHold = dealerCards)

        // when
        dealer.getMoneyFromPlayer(gamePlayer, 10000)
        dealer.compareScore(gamePlayer)

        // then
        dealer.money.value shouldBe -15000
        gamePlayer.money.value shouldBe 15000
    }

    @Test
    fun `딜러만 블랙잭인 경우 딜러는 무조건 이긴다`() {
        // given
        val sampleCard = Card.createCard(CardRank.JACK, CardShape.CLOVER)
        val myCards = CardHold(mutableListOf(sampleCard))
        val gamePlayer = GamePlayer("goofy", myCards)

        val sampleCard2 = Card.createCard(CardRank.JACK, CardShape.HEART)
        val sampleCard3 = Card.createCard(CardRank.ACE, CardShape.HEART)
        val dealerCards = CardHold(mutableListOf(sampleCard2, sampleCard3))
        val dealer = Dealer(cardHold = dealerCards)

        // when
        dealer.getMoneyFromPlayer(gamePlayer, 10000)
        dealer.compareScore(gamePlayer)

        // then
        dealer.money.value shouldBe 10000
        gamePlayer.money.value shouldBe -10000
    }

    @Test
    fun `둘다 21점 미만인 경우 카드 포인트가 큰 사람이 이긴다`() {
        // given
        val sampleCard = Card.createCard(CardRank.JACK, CardShape.CLOVER)
        val myCards = CardHold(mutableListOf(sampleCard))
        val gamePlayer = GamePlayer("goofy", myCards)

        val sampleCard2 = Card.createCard(CardRank.TWO, CardShape.HEART)
        val dealerCards = CardHold(mutableListOf(sampleCard2))
        val dealer = Dealer(cardHold = dealerCards)

        // when
        dealer.getMoneyFromPlayer(gamePlayer, 10000)
        dealer.compareScore(gamePlayer)

        // then
        dealer.money.value shouldBe -10000
        gamePlayer.money.value shouldBe 10000
    }
}
