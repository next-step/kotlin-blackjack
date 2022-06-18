package blackjack.domain.participant.dealer

import blackjack.domain.Amount
import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardDeckTest
import blackjack.domain.card.type.Ace
import blackjack.domain.card.type.Suit
import blackjack.domain.card.type.Ten
import blackjack.domain.card.type.Two
import blackjack.domain.participant.player.Player
import blackjack.domain.participant.player.Players
import blackjack.domain.participant.type.Status
import blackjack.domain.participant.vo.BetAmount
import blackjack.domain.participant.vo.CardsInHand
import blackjack.domain.participant.vo.Name
import blackjack.domain.participant.vo.ParticipantInformation
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

    "승리시 플레이어의 베팅 금액을 획득한다." {
        val dealer = Dealer(BLACKJACK_CARDS)
        val players = Players(
            listOf(
                Player(ParticipantInformation(Name("dean"), Status.PLAY), TWO_CARD, BetAmount.of(1_000)),
                Player(ParticipantInformation(Name("dean2"), Status.PLAY), TWO_CARD, BetAmount.of(1_000))
            )
        )
        dealer.score(DealerScoreStrategy(players))

        dealer.winningAmount.amount shouldBe Amount(2_000)
    }

    "패배시 플레이어의 베팅 금액 만큼 지급한다." {
        val dealer = Dealer(TWO_CARD)
        val players = Players(
            listOf(
                Player(ParticipantInformation(Name("dean"), Status.PLAY), ACE_CARD, BetAmount.of(1_000)),
                Player(ParticipantInformation(Name("dean2"), Status.PLAY), ACE_CARD, BetAmount.of(1_000))
            )
        )

        dealer.score(DealerScoreStrategy(players))

        dealer.winningAmount.amount shouldBe Amount(-2_000)
    }

    "플레이어와 점수가 같을 경우 베팅 금액을 획득한다." {
        val dealer = Dealer(ACE_CARD)
        val players = Players(
            listOf(
                Player(ParticipantInformation(Name("dean"), Status.PLAY), ACE_CARD, BetAmount.of(1_000)),
                Player(ParticipantInformation(Name("dean2"), Status.PLAY), ACE_CARD, BetAmount.of(1_000))
            )
        )

        dealer.score(DealerScoreStrategy(players))

        dealer.winningAmount.amount shouldBe Amount(2_000)
    }

    "플레이어가 블랙잭일 경우 베팅 금액의 1.5배를 지급한다." {
        val dealer = Dealer(ACE_CARD)

        val players = Players(
            listOf(
                Player(ParticipantInformation(Name("dean"), Status.PLAY), BLACKJACK_CARDS, BetAmount.of(1_000)),
                Player(ParticipantInformation(Name("dean2"), Status.PLAY), BLACKJACK_CARDS, BetAmount.of(1_000))
            )
        )

        dealer.score(DealerScoreStrategy(players))

        dealer.winningAmount.amount shouldBe Amount(-3_000)
    }

    "플레이어와 딜러 모두 블랙잭일 경우 베팅 금액을 지급한다." {
        val dealer = Dealer(BLACKJACK_CARDS)

        val players = Players(
            listOf(
                Player(ParticipantInformation(Name("dean"), Status.PLAY), BLACKJACK_CARDS, BetAmount.of(1_000)),
                Player(ParticipantInformation(Name("dean2"), Status.PLAY), BLACKJACK_CARDS, BetAmount.of(1_000))
            )
        )

        dealer.score(DealerScoreStrategy(players))

        dealer.winningAmount.amount shouldBe Amount(-2_000)
    }
}) {
    companion object {
        val BLACKJACK_CARDS = CardsInHand(listOf(Card(Ace(), Suit.HEART), Card(Ten(), Suit.HEART)))
        val TWO_CARD = CardsInHand(listOf(Card(Two(), Suit.HEART)))
        val ACE_CARD = CardsInHand(listOf(Card(Ace(), Suit.HEART)))
    }
}
