package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.domain.player.Dealer
import blackjack.domain.player.Gamer
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GameResultTest : StringSpec({

    "딜러와 게이머 카드에 따라 승패를 결정한다." {
        val dealer = Dealer("Dealer")
        val gamer = Gamer("Gamer")

        dealer.addCardToDeck(Card(Denomination.TWO, Suit.CLUBS))
        gamer.addCardToDeck(Card(Denomination.KING, Suit.HEARTS))

        val result = GameResult.calculate(dealer, gamer)

        result shouldBe GameResult.WIN
    }

    "reserve는 결과를 반대로 반환한다." {
        val result1 = GameResult.WIN.reverse()
        val result2 = GameResult.LOSE.reverse()

        result1 shouldBe GameResult.LOSE
        result2 shouldBe GameResult.WIN
    }

    "딜러 카드 점수가 21 이상인 경우, 게이머가 승리한다." {
        val dealer = Dealer()
        val gamer = Gamer("gray")

        dealer.addCardToDeck(Card(Denomination.KING, Suit.CLUBS))
        dealer.addCardToDeck(Card(Denomination.JACK, Suit.CLUBS))
        dealer.addCardToDeck(Card(Denomination.QUEEN, Suit.CLUBS))

        val result = GameResult.calculate(dealer, gamer)

        result shouldBe GameResult.WIN
    }
})
