package blackjack.domain.player

import blackjack.domain.card.Denomination
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.Suit
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    "딜러를 생성한다" {
        val dealer = Dealer()
        dealer.cards().size shouldBe 0
        dealer.isRunning() shouldBe true
    }

    "딜러의 카드 합이 16 이하이면 추가로 카드를 받아야 한다" {
        val dealer = Dealer()
        dealer.drawCard(PlayingCard.of(Suit.HEARTS, Denomination.SIX))
        dealer.drawCard(PlayingCard.of(Suit.SPADES, Denomination.TEN))

        dealer.needsMoreCard() shouldBe true
    }

    "딜러의 카드 합이 17 이상이면 추가로 카드를 받지 않는다" {
        val dealer = Dealer()
        dealer.drawCard(PlayingCard.of(Suit.HEARTS, Denomination.SEVEN))
        dealer.drawCard(PlayingCard.of(Suit.SPADES, Denomination.TEN))

        dealer.needsMoreCard() shouldBe false
    }

    "딜러가 블랙잭을 받으면 게임이 종료된다" {
        val dealer = Dealer()
        dealer.drawCard(PlayingCard.of(Suit.HEARTS, Denomination.ACE))
        dealer.drawCard(PlayingCard.of(Suit.SPADES, Denomination.KING))

        dealer.isRunning() shouldBe false
    }

    "딜러가 bust되면 게임이 종료된다" {
        val dealer = Dealer()
        dealer.drawCard(PlayingCard.of(Suit.HEARTS, Denomination.KING))
        dealer.drawCard(PlayingCard.of(Suit.SPADES, Denomination.QUEEN))
        dealer.drawCard(PlayingCard.of(Suit.DIAMONDS, Denomination.JACK))

        dealer.isRunning() shouldBe false
    }
})
