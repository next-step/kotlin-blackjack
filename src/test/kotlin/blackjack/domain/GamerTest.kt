package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GamerTest : StringSpec({

    val dealer = Dealer(OwnCards(0))
    dealer.ownCards.addCard(Card(CardNumber.SIX))

    "상태를 승리로 변경한다" {
        val gamer = Gamer("a", OwnCards(0))
        gamer.ownCards.addCard(Card(CardNumber.SEVEN))
        gamer.changeState(dealer)
        gamer.state shouldBe State.WIN
    }

    "상태를 패배로 변경한다" {
        val gamer = Gamer("a", OwnCards(0))
        gamer.ownCards.addCard(Card(CardNumber.FIVE))
        gamer.changeState(dealer)
        gamer.state shouldBe State.LOSE
    }

    "상태를 무승부로 변경한다" {
        val gamer = Gamer("a", OwnCards(0))
        gamer.ownCards.addCard(Card(CardNumber.SIX))
        gamer.changeState(dealer)
        gamer.state shouldBe State.DRAW
    }

    "상태를 블랙잭으로 변경한다" {
        val gamer = Gamer("a", OwnCards(0), true)
        gamer.ownCards.addCard(Card(CardNumber.EIGHT))
        gamer.changeState(dealer)
        gamer.state shouldBe State.BLACKJACK
    }
})
