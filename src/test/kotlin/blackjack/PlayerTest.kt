package blackjack

import blackjack.domain.Card
import blackjack.domain.CardRank
import blackjack.domain.CardSuit
import blackjack.domain.Player
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.math.exp

internal class PlayerTest {

    @DisplayName("플레이어는 여러 장의 카드를 가지고 있다.")
    @Test
    fun playerHasCards() {
        val cards = listOf(
            Card.of(CardSuit.DIAMOND, CardRank.ACE),
            Card.of(CardSuit.HEART, CardRank.ACE)
        )
        val actual = Player()
        actual.addCards(cards)

        actual.cards shouldBe cards
    }

    @DisplayName("플레이어는 한장의 카드를 추가할 수 있다.")
    @Test
    fun addCard() {
        val card = Card.of(CardSuit.DIAMOND, CardRank.ACE)
        val actual = Player()
        actual.addCard(card)

        actual.cards.size shouldBe 1
        actual.cards.shouldContain(card)
    }

    @DisplayName("플레이어는 손패의 합을 알 수 있다.(Ace 제외)")
    @Test
    fun sum() {
        val testCases = listOf(
            listOf(
                Card.of(CardSuit.DIAMOND, CardRank.TWO),
                Card.of(CardSuit.DIAMOND, CardRank.THREE),
            ) to 5,
            listOf(
                Card.of(CardSuit.HEART, CardRank.FOUR),
                Card.of(CardSuit.HEART, CardRank.FIVE),
                Card.of(CardSuit.HEART, CardRank.KING),
            ) to 19
        )

        testCases.forAll { testCase ->
            val player = Player()
            player.addCards(testCase.first)
            val actual = player.sum()
            val expect = testCase.second

            actual shouldBe expect
        }
    }

    @DisplayName("플레이어의 canDraw 값은 카드 합이 21 이하면 true 초과면 false이다.")
    @Test
    fun canDraw() {
        val testCases = listOf(
            listOf(
                Card.of(CardSuit.DIAMOND, CardRank.TWO),
                Card.of(CardSuit.DIAMOND, CardRank.THREE),
            ) to true,
            listOf(
                Card.of(CardSuit.HEART, CardRank.JACK),
                Card.of(CardSuit.HEART, CardRank.QUEEN),
                Card.of(CardSuit.HEART, CardRank.KING),
            ) to false
        )

        testCases.forAll { testCase ->
            val player = Player()
            player.addCards(testCase.first)
            val actual = player.canDraw
            val expect = testCase.second

            actual shouldBe expect
        }
    }
}
