package blackjack.domain

import blackjack.domain.Rule
import blackjack.domain.model.Card
import blackjack.domain.model.CardType
import blackjack.domain.model.CardValue
import blackjack.domain.model.Cards
import blackjack.domain.model.Dealer
import blackjack.domain.model.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class RuleTest {
    @Test
    fun `딜러와 플레이어 중에 21에 가까운 사람이 승리한다 - 딜러 승리`() {
        val dealerCard =
            mutableListOf(Card.from(CardType.CLOVER, CardValue.ACE), Card.from(CardType.SPADE, CardValue.NINE))
        val dealer = Dealer(cards = Cards(dealerCard))

        val playerCards =
            mutableListOf(Card.from(CardType.SPADE, CardValue.TEN), Card.from(CardType.HEART, CardValue.NINE))
        val player = Player(cards = Cards(playerCards))

        Rule.decisionWinner(dealer, player) shouldBe dealer
    }

    @Test
    fun `딜러와 플레이어 중에 21에 가까운 사람이 승리한다 - 플레이어 승리`() {
        val dealerCard =
            mutableListOf(Card.from(CardType.CLOVER, CardValue.EIGHT), Card.from(CardType.SPADE, CardValue.NINE))
        val dealer = Dealer(cards = Cards(dealerCard))

        val playerCards =
            mutableListOf(Card.from(CardType.SPADE, CardValue.TEN), Card.from(CardType.HEART, CardValue.NINE))
        val player = Player(cards = Cards(playerCards))

        Rule.decisionWinner(dealer, player) shouldBe player
    }

    @Test
    fun `딜러와 플레이어의 카드가 같으면 승자가 없다`() {
        val dealerCard =
            mutableListOf(Card.from(CardType.CLOVER, CardValue.EIGHT), Card.from(CardType.SPADE, CardValue.NINE))
        val dealer = Dealer(cards = Cards(dealerCard))

        val playerCards =
            mutableListOf(Card.from(CardType.SPADE, CardValue.TEN), Card.from(CardType.HEART, CardValue.SEVEN))
        val player = Player(cards = Cards(playerCards))

        Rule.decisionWinner(dealer, player) shouldBe null
    }

    @Test
    fun `플레이어의 카드의 합이 21을 넘어가면 딜러가 이긴다`() {
        val dealerCard =
            mutableListOf(Card.from(CardType.CLOVER, CardValue.TEN), Card.from(CardType.SPADE, CardValue.NINE))
        val dealer = Dealer(cards = Cards(dealerCard))

        val playerCards = mutableListOf(
            Card.from(CardType.SPADE, CardValue.TEN),
            Card.from(CardType.HEART, CardValue.SEVEN),
            Card.from(CardType.DIAMOND, CardValue.SIX)
        )
        val player = Player(cards = Cards(playerCards))

        Rule.decisionWinner(dealer, player) shouldBe dealer
    }

    @Test
    fun `딜러의 카드의 합이 21을 넘고 플레이어의 카드의 합이 21 이하일 때 플레이어가 이긴다`() {
        val dealerCard = mutableListOf(
            Card.from(CardType.SPADE, CardValue.TEN),
            Card.from(CardType.HEART, CardValue.SEVEN),
            Card.from(CardType.DIAMOND, CardValue.SIX)
        )
        val dealer = Dealer(cards = Cards(dealerCard))

        val playerCards =
            mutableListOf(
                Card.from(CardType.SPADE, CardValue.TWO),
                Card.from(CardType.HEART, CardValue.JACK),
                Card.from(CardType.DIAMOND, CardValue.FIVE)
            )
        val player = Player(cards = Cards(playerCards))

        Rule.decisionWinner(dealer, player) shouldBe player
    }
}
