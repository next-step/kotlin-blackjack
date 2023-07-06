package blackjack.domain

import blackjack.domain.enums.Condition
import blackjack.domain.enums.MatchResult
import blackjack.enums.Rank
import blackjack.enums.Symbol
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {

    private lateinit var dealer: Dealer

    @BeforeEach
    fun setup() {
        dealer = Dealer(
            cards = Cards(
                listOf(
                    Card(rank = Rank.JACK, symbol = Symbol.SPADES),
                    Card(rank = Rank.FIVE, symbol = Symbol.HEARTS)
                )
            ),
            condition = Condition.PLAY,
            deck = Deck()
        )
    }

    @Test
    fun `딜러의 카드 점수의 합과 플레이어의 카드 점수의 합을 비교해 높으면 승을 반환`() {

        val player = Player(
            name = "",
            cards = Cards(
                cards = listOf(
                    Card(rank = Rank.SIX, symbol = Symbol.SPADES),
                    Card(rank = Rank.FIVE, symbol = Symbol.HEARTS)
                )
            ),
            condition = Condition.PLAY,
            betAmount = 10000.0
        )

        dealer.determineResult(player) shouldBe MatchResult.WIN
    }

    @Test
    fun `딜러의 카드 점수의 합과 플레이어의 카드 점수의 합을 비교해 낮다면 패를 반환`() {

        val player = Player(
            name = "",
            cards = Cards(
                cards = listOf(
                    Card(rank = Rank.SIX, symbol = Symbol.SPADES),
                    Card(rank = Rank.KING, symbol = Symbol.HEARTS)
                )
            ),
            condition = Condition.PLAY,
            betAmount = 10000.0
        )

        dealer.determineResult(player) shouldBe MatchResult.LOSE
    }

    @Test
    fun `딜러의 카드 점수의 합과 플레이어의 카드 점수의 합을 비교해 같다면 무승부를 반환`() {

        val player = Player(
            name = "",
            cards = Cards(
                cards = listOf(
                    Card(rank = Rank.KING, symbol = Symbol.SPADES),
                    Card(rank = Rank.FIVE, symbol = Symbol.HEARTS)
                )
            ),
            condition = Condition.PLAY,
            betAmount = 10000.0
        )

        dealer.determineResult(player) shouldBe MatchResult.DRAW
    }

    @Test
    fun `딜러는 이름과 카드 및 덱을 가진다`() {
        val deck = Deck()
        val dealer = Dealer(name = "딜러", cards = deck.drawCard(2), deck = deck)

        dealer.name shouldBe "딜러"
        dealer.cards.cards.size shouldBe BASIC_CARD_COUNT
        dealer.deck.cardCount shouldBe TOTAL_CARD_COUNT - BASIC_CARD_COUNT
    }

    @Test
    fun `딜러는 처음 받은 2장의 카드의 합이 17 이상이면 상태는 그대로 유지한다`() {
        val deck = Deck()
        val cards = Cards(listOf(Card(rank = Rank.SEVEN, symbol = Symbol.SPADES), Card(rank = Rank.KING, symbol = Symbol.SPADES)))
        val dealer = Dealer(name = "딜러", cards = cards, deck = deck)
        dealer.currentCondition() shouldBe Condition.STAY
    }

    @Test
    fun `딜러는 처음 받은 2장의 카드의 합이 16 이하면 1장의 카드를 추가로 받기 위한 상태로 변한다`() {
        val deck = Deck()
        val cards = Cards(listOf(Card(rank = Rank.TWO, symbol = Symbol.SPADES), Card(rank = Rank.KING, symbol = Symbol.SPADES)))
        val dealer = Dealer(name = "딜러", cards = cards, deck = deck)
        dealer.currentCondition() shouldBe Condition.PLAY
    }

    companion object {
        const val TOTAL_CARD_COUNT = 52
        const val BASIC_CARD_COUNT = 2
    }
}
