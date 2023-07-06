package blackjack.domain

import blackjack.domain.card.CardNumber
import blackjack.test.FakeGenerator
import blackjack.test.TestUtils
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class BlackJackGameTest : BehaviorSpec({
    given("블랙잭 게임") {
        val deck = Deck.create()
        val game = BlackJackGame(deck)

        `when`("게임을 시작하면") {
            val initPlayers = Players.of(List(2) { TestUtils.randomString(size = 2) })
            val result = game.start(initPlayers)

            then("각 플레이어는 2장의 카드를 받는다.") {
                result[0].hand.cards.size shouldBe 2
                result[1].hand.cards.size shouldBe 2
            }
        }

        `when`("play - 플레이어의 점수가 21점이면 ") {
            val cards = listOf(
                FakeGenerator.card(CardNumber.ACE),
                FakeGenerator.card(CardNumber.TEN)
            )
            val p1 = Player("p1").addCards(cards)
            val initPlayers = Players(listOf(p1))
            val result = game.play(initPlayers, { false }, {})

            then("카드를 받을 수 없다.") {
                result[0].hand.cards shouldContainExactly cards
            }
        }

        `when`("play - 게임 지속 조건이 false이면") {
            val cards = listOf(
                FakeGenerator.card(CardNumber.TWO),
                FakeGenerator.card(CardNumber.TEN)
            )
            val p1 = Player("p1").addCards(cards)
            val initPlayers = Players(listOf(p1))

            val result = game.play(initPlayers, { false }, {})
            then("카드를 받을 수 없다.") {
                result[0].hand.cards shouldContainExactly cards
            }
        }
    }
})
