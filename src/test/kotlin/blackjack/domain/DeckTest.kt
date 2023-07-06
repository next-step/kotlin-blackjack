package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DeckTest : FunSpec({
    context("덱에 들어있는 카드의 종류는 스페이드, 하트, 클로버, 다이아몬드 4가지로 그룹을 가진다. 각 그룹별 13장의 카드가 있다.") {
        val deck = Deck()

        val spades = deck.cards.filter { it.suit == Suit.SPADE }
        val hearts = deck.cards.filter { it.suit == Suit.HEART }
        val clubs = deck.cards.filter { it.suit == Suit.CLUB }
        val diamonds = deck.cards.filter { it.suit == Suit.DIAMOND }

        spades.size shouldBe 13
        hearts.size shouldBe 13
        clubs.size shouldBe 13
        diamonds.size shouldBe 13
    }

    context("덱에서 카드를 뽑을 수 있다.") {
        val deck = Deck()
        val card = deck.drawCard()
        deck.cards.size shouldBe 51
        deck.cards.contains(card) shouldBe false
    }
})
