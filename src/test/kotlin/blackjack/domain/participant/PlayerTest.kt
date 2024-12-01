package blackjack.domain.participant

import blackjack.domain.betting.Betting
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import blackjack.domain.createBlackjackCardsFixture
import blackjack.domain.createBustCardsFixture
import blackjack.domain.createCardsFixture
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "플레이어는 이름과 소유한 카드 정보를 갖는다." {
        val card = Card(shape = CardShape.Heart, number = CardNumber.Two)
        val cards = Cards()
        cards.add(card)
        val player = Player(name = "홍길동", cards = cards, betting = Betting(100))

        val cardResult = player.cards.getCards()[0]
        player.name shouldBe "홍길동"
        cardResult.shape shouldBe CardShape.Heart
        cardResult.number shouldBe CardNumber.Two
    }

    "플레이어는 카드를 받을 수 있다." {
        val player = Player(name = "홍길동", betting = Betting(100))
        val card = Card(shape = CardShape.Heart, number = CardNumber.Ace)

        player.receiveCard(card)

        player.cards.getCards()[0] shouldBe card
    }

    "플레이어는 소유한 카드의 다음 받을 카드 포함 소유한 모든 카드 숫자 합이 21이 넘으면 카드를 받을 수 없다." {
        val cards = createBustCardsFixture()

        val player = Player(name = "홍길동", cards = cards, betting = Betting(100))

        player.canReceive() shouldBe false
    }

    "calculateRate()는 딜러가 버스트면 플레이어의 패와 상관없이 배당은 1.0 이다" {
        val cards = createBustCardsFixture()
        val dealer = Dealer("", cards = cards)
        val player = Player(betting = Betting(0), name = "player", cards = createBlackjackCardsFixture())

        player.calculateRate(dealer) shouldBe 1.0
    }

    "calculateRate()는 플레이어의 처음 두장의 카드가 블랙잭이고 딜러가 버스트가 아니라면 배당은 1.5이다" {
        val cards =
            createCardsFixture(
                Card(shape = CardShape.Heart, number = CardNumber.Five),
                Card(shape = CardShape.Spade, number = CardNumber.Four),
            )
        val dealer = Dealer("", cards = cards)
        val player = Player(betting = Betting(0), name = "player", cards = createBlackjackCardsFixture())

        player.calculateRate(dealer) shouldBe 1.5
    }

    "calculateRate()는 플레이어의 카드가 버스트라면 배당은 -1.0이다" {
        val cards =
            createCardsFixture(
                Card(shape = CardShape.Heart, number = CardNumber.Five),
                Card(shape = CardShape.Spade, number = CardNumber.Four),
            )
        val dealer = Dealer("", cards = cards)
        val player = Player(betting = Betting(0), name = "player", cards = createBustCardsFixture())

        player.calculateRate(dealer) shouldBe -1.0
    }

    "calculateRate()는 딜러, 플레이어 모두 버스트가 아니고 플레이어의 점수가 높으면 배당은 1.0이다" {
        val dealerCards =
            createCardsFixture(
                Card(shape = CardShape.Heart, number = CardNumber.Five),
                Card(shape = CardShape.Spade, number = CardNumber.Four),
            )
        val playerCards =
            createCardsFixture(
                Card(shape = CardShape.Heart, number = CardNumber.Five),
                Card(shape = CardShape.Spade, number = CardNumber.Five),
            )
        val dealer = Dealer("", cards = dealerCards)
        val player = Player(betting = Betting(0), name = "player", cards = playerCards)

        player.calculateRate(dealer) shouldBe 1.0
    }

    "calculateRate()는 딜러, 플레이어 모두 버스트가 아니고 딜러의 점수가 높으면 배당은 -1.0이다" {
        val dealerCards =
            createCardsFixture(
                Card(shape = CardShape.Heart, number = CardNumber.Five),
                Card(shape = CardShape.Spade, number = CardNumber.Five),
            )
        val playerCards =
            createCardsFixture(
                Card(shape = CardShape.Heart, number = CardNumber.Five),
                Card(shape = CardShape.Spade, number = CardNumber.Four),
            )
        val dealer = Dealer("", cards = dealerCards)
        val player = Player(betting = Betting(0), name = "player", cards = playerCards)

        player.calculateRate(dealer) shouldBe -1.0
    }

    "calculateRate()는 플레이어 처음 두 장 패가 블랙잭이 아니고 딜러, 플레이어 모두 블랙잭이면 1.0이다" {
        val dealerCards =
            createCardsFixture(
                Card(shape = CardShape.Heart, number = CardNumber.Ten),
                Card(shape = CardShape.Spade, number = CardNumber.Ace),
            )
        val playerCards =
            createCardsFixture(
                Card(shape = CardShape.Heart, number = CardNumber.Ten),
                Card(shape = CardShape.Spade, number = CardNumber.Four),
                Card(shape = CardShape.Spade, number = CardNumber.Seven),
            )
        val dealer = Dealer("", cards = dealerCards)
        val player = Player(betting = Betting(0), name = "player", cards = playerCards)

        player.calculateRate(dealer) shouldBe 1.0
    }
})
