package blackjack.core.player

import blackjack.core.amount.BettingAmount
import blackjack.core.card.Card
import blackjack.core.card.Denomination
import blackjack.core.card.Suit
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import java.util.stream.Stream

class DealerTest {
    @DisplayName("승패를 테스트")
    @TestFactory
    fun dynamicTestsFromCollection(): Stream<DynamicTest> {
        val dealer = Dealer()
        val player = Player(Name("test"))

        return Stream.of(
            dynamicTest("비겼을 때") {
                dealer.draw(Card(Denomination.ACE, Suit.HEARTS))
                player.draw(Card(Denomination.ACE, Suit.CLUBS))
                dealer.checkMatch(player) shouldBe MatchResult.DRAW
            },
            dynamicTest("딜러가 이겼을 때") {
                dealer.draw(Card(Denomination.TWO, Suit.HEARTS))
                dealer.checkMatch(player) shouldBe MatchResult.WIN
            },
            dynamicTest("딜러가 졌을 때") {
                player.draw(Card(Denomination.THREE, Suit.CLUBS))
                dealer.checkMatch(player) shouldBe MatchResult.LOSE
            },
        )
    }

    @Test
    fun `딜러의 버스트상태의 승패를 테스트`() {
        val dealer = Dealer()
        dealer.draw(Card(Denomination.KING, Suit.HEARTS))
        dealer.draw(Card(Denomination.JACK, Suit.HEARTS))
        dealer.draw(Card(Denomination.QUEEN, Suit.HEARTS))

        val player = Player(Name("test"))
        player.draw(Card(Denomination.ACE, Suit.CLUBS))

        dealer.checkMatch(player) shouldBe MatchResult.LOSE
    }

    @Test
    fun `플레이어의 버스트상태의 승패를 테스트`() {
        val dealer = Dealer()
        dealer.draw(Card(Denomination.ACE, Suit.CLUBS))

        val player = Player(Name("test"))
        player.draw(Card(Denomination.KING, Suit.HEARTS))
        player.draw(Card(Denomination.JACK, Suit.HEARTS))
        player.draw(Card(Denomination.QUEEN, Suit.HEARTS))

        dealer.checkMatch(player) shouldBe MatchResult.WIN
    }

    @Test
    fun `비겼을때 수익 계산 테스트`() {
        val dealer = Dealer()
        dealer.draw(Card(Denomination.KING, Suit.CLUBS))
        dealer.draw(Card(Denomination.JACK, Suit.CLUBS))

        val player = Player(Name("test"), BettingAmount(1000))
        player.draw(Card(Denomination.KING, Suit.HEARTS))
        player.draw(Card(Denomination.JACK, Suit.HEARTS))

        val players = Players(listOf(player))
        dealer.calculateProfit(players)

        dealer.profitAmount.amount shouldBe 0
        player.profitAmount.amount shouldBe 0
    }

    @Test
    fun `딜러가 이겼을때 수익 계산 테스트`() {
        val dealer = Dealer()
        dealer.draw(Card(Denomination.KING, Suit.CLUBS))
        dealer.draw(Card(Denomination.JACK, Suit.CLUBS))

        val player = Player(Name("test"), BettingAmount(1000))
        player.draw(Card(Denomination.KING, Suit.HEARTS))
        player.draw(Card(Denomination.JACK, Suit.HEARTS))
        player.draw(Card(Denomination.QUEEN, Suit.HEARTS))

        val players = Players(listOf(player))
        dealer.calculateProfit(players)

        dealer.profitAmount.amount shouldBe 1000
        player.profitAmount.amount shouldBe -1000
    }

    @Test
    fun `딜러가 졌을때 수익 계산 테스트`() {
        val dealer = Dealer()
        dealer.draw(Card(Denomination.KING, Suit.HEARTS))
        dealer.draw(Card(Denomination.JACK, Suit.HEARTS))
        dealer.draw(Card(Denomination.QUEEN, Suit.HEARTS))

        val player = Player(Name("test"), BettingAmount(1000))
        player.draw(Card(Denomination.KING, Suit.HEARTS))
        player.draw(Card(Denomination.JACK, Suit.HEARTS))

        val players = Players(listOf(player))
        dealer.calculateProfit(players)

        dealer.profitAmount.amount shouldBe -1000
        player.profitAmount.amount shouldBe 1000
    }

    @Test
    fun `딜러가 블랙잭에 졌을때 수익 계산 테스트`() {
        val dealer = Dealer()
        dealer.draw(Card(Denomination.KING, Suit.HEARTS))
        dealer.draw(Card(Denomination.JACK, Suit.HEARTS))

        val player = Player(Name("test"), BettingAmount(1000))
        player.draw(Card(Denomination.ACE, Suit.HEARTS))
        player.draw(Card(Denomination.JACK, Suit.HEARTS))

        val players = Players(listOf(player))
        dealer.calculateProfit(players)

        dealer.profitAmount.amount shouldBe -1500
        player.profitAmount.amount shouldBe 1500
    }

    @Test
    fun `딜러와 플레이어가 둘다 버스트 였을 때 수익 계산 테스트`() {
        val dealer = Dealer()
        dealer.draw(Card(Denomination.KING, Suit.HEARTS))
        dealer.draw(Card(Denomination.JACK, Suit.HEARTS))
        dealer.draw(Card(Denomination.QUEEN, Suit.HEARTS))

        val player = Player(Name("test"), BettingAmount(1000))
        player.draw(Card(Denomination.ACE, Suit.HEARTS))
        player.draw(Card(Denomination.JACK, Suit.HEARTS))
        player.draw(Card(Denomination.QUEEN, Suit.HEARTS))

        val players = Players(listOf(player))
        dealer.calculateProfit(players)

        dealer.profitAmount.amount shouldBe -1000
        player.profitAmount.amount shouldBe 1000
    }
}
