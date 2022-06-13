package blackjack.domain.participant

import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardDeckTest
import blackjack.domain.participant.vo.CardsInHand
import blackjack.domain.participant.vo.Name
import blackjack.domain.participant.vo.WinningScore
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    "딜러 객체를 생성할수 있다." {
        shouldNotThrow<Throwable> { Dealer(CardsInHand()) }
    }

    "블랙젝 게임 준비시 카드의 합이 16이하 인경우 카드를 한장 더 받는다." {
        val dealer = Dealer(CardsInHand())
        val cardDeck = CardDeckTest.sortedCardDeck()

        dealer.ready(cardDeck)

        dealer.cardsInHand.cards.size shouldBe 3
    }

    "블랙젝 게임 준비시 카드의 합이 17이상 인경우 카드를 2장 받는다." {
        val dealer = Dealer(CardsInHand())
        val cardDeck = CardDeck(CardDeckTest.sortedCardDeck().cards.reversed())

        dealer.ready(cardDeck)

        dealer.cardsInHand.cards.size shouldBe 2
    }

    "카드를 뽑을수 있다." {
        val dealer = Dealer(CardsInHand())
        val cardDeck = CardDeckTest.cardDeck()

        dealer.hit(cardDeck)

        dealer.cardsInHand.cards.size shouldBe 1
    }

    "17점 이상 21점 이하인 경우 Stay 상태로 변경한다." {
        val dealer = Dealer(CardsInHand())
        val cardDeck = CardDeck(CardDeckTest.sortedCardDeck().cards.reversed())

        dealer.ready(cardDeck)

        dealer.participantInformation.isStay() shouldBe true
    }

    "17점 초과시 카드를 뽑을수 없다." {
        val dealer = Dealer(CardsInHand())
        val cardDeck = CardDeck(CardDeckTest.sortedCardDeck().cards.reversed())

        dealer.ready(cardDeck)

        shouldThrow<IllegalArgumentException> { dealer.hit(cardDeck) }
    }

    "플레이어들의 점수와 비교하여 최종 결과를 알수 있다." {
        val dealer = Dealer(CardsInHand())
        val reverseCardDeck = CardDeck(CardDeckTest.sortedCardDeck().cards.reversed())
        val sortedCardDeck = CardDeckTest.sortedCardDeck()

        dealer.ready(reverseCardDeck)

        val win = Player.sit(Name("win"))
        win.ready(sortedCardDeck)

        val lose = Player.sit(Name("lose"))
        lose.ready(reverseCardDeck)
        lose.hit(sortedCardDeck)

        val draw = Player.sit(Name("draw"))
        draw.ready(reverseCardDeck)

        val players = listOf(win, lose, draw)
        dealer.score(players)

        dealer.winningScores.values shouldBe listOf(WinningScore.WIN, WinningScore.LOSE, WinningScore.DRAW)
    }
})
