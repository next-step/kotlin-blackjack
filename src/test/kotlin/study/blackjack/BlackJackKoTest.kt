package study.blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.PokerSymbol
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeOneOf
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class BlackJackKoTest : StringSpec({
    "참여자들에게 카드를 두 장 나누어 진다" {
        val deck = Deck()
        val dealer = Dealer(deck)
        val players = arrayOf(Player("test1"), Player("test2"))
        dealer.startRound(players)
        players.forEach { it.hands().size shouldBe 2 }
    }

    "덱에서 한장을 뽑는다." {
        val deck = Deck()
        val dealer = Dealer(deck)
        val cards = List(10) { dealer.draw() }
        val pokerSymbols = PokerSymbol.values().toList()

        cards.shouldNotBeNull()
        cards.forEach {
            it.symbol shouldBeOneOf pokerSymbols
            it.value shouldBeInRange (1..11)
        }
    }

    "덱이 소진되면 에러를 내보낸다." {
        val deck = Deck()
        val dealer = Dealer(deck)
        val cards = List(52) { dealer.draw() }
        val pokerSymbols = PokerSymbol.values().toList()

        cards.shouldNotBeNull()
        cards.forEach {
            it.symbol shouldBeOneOf pokerSymbols
            it.value shouldBeInRange (1..11)
        }
        shouldThrow<IllegalArgumentException> {
            dealer.draw()
        }.message shouldBe "모든 덱이 소진되었습니다."
    }
})
