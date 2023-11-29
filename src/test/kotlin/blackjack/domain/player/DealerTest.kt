package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Character
import blackjack.domain.card.Suit
import blackjack.domain.cards.Deck
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    "Dealer 는 Bust 패배한다" {
        val dealer = Dealer(Deck.fullDeck())
        dealer.initHand()

        while (dealer.asPlayer.state.isHit()) {
            dealer.asPlayer.addCard(dealer.provideCard())
        }

        val player = Player("pp", dealer.createInitialHand())

        dealer.asPlayer.state.isBust() shouldBe true
        dealer.wins(player) shouldBe false
    }

    "Dealer 는 동점이면 패배한다" {
        val dealer = Dealer(Deck.fullDeck())
        dealer.initHand()

        dealer.asPlayer.toTargetValueOver19(20)

        val player = Player("pp", dealer.createInitialHand())

        player.toTargetValueOver19(20)

        dealer.asPlayer.hand.valueSum() shouldBe 20
        player.hand.valueSum() shouldBe 20

        dealer.wins(player) shouldBe false
    }

    "Dealer 승리 테스트" {
        val dealer = Dealer(Deck.fullDeck())
        dealer.initHand()

        dealer.asPlayer.toTargetValueOver19(21)

        val player = Player("pp", dealer.createInitialHand())

        player.toTargetValueOver19(20)

        dealer.asPlayer.hand.valueSum() shouldBe 21
        player.hand.valueSum() shouldBe 20

        dealer.wins(player) shouldBe true
    }

    "Dealer process turn 테스트" {
        val dealer = Dealer(Deck.fullDeck())
        dealer.initHand()

        dealer.asPlayer.toTargetValueOver19(20)

        dealer.processTurn {
            it shouldBe false
        }
    }
})

private fun Player.toTargetValueOver19(targetValue: Int) {
    var remain = targetValue - hand.valueSum()

    while (remain != 0) {
        val cardValue = if (remain > 10) remain - 10 else remain
        addCard(Card(Suit.Diamond, Character.values().first { it.value == cardValue }))
        remain -= cardValue
    }
}
