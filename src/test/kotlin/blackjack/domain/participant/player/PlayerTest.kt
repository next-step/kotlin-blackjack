package blackjack.domain.participant.player

import blackjack.domain.Amount
import blackjack.domain.card.Card
import blackjack.domain.card.CardDeckTest
import blackjack.domain.card.type.Ace
import blackjack.domain.card.type.Suit
import blackjack.domain.card.type.Ten
import blackjack.domain.card.type.Two
import blackjack.domain.participant.dealer.Dealer
import blackjack.domain.participant.type.Status
import blackjack.domain.participant.vo.BetAmount
import blackjack.domain.participant.vo.CardsInHand
import blackjack.domain.participant.vo.Name
import blackjack.domain.participant.vo.ParticipantInformation
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "참가자 객체를 생성할수 있다." {
        shouldNotThrow<Throwable> { Player.sit(Name("dean"), BetAmount.of(1_000)) }
    }

    "Stay 상태로 변경할수 있다" {
        val player = player()

        player.stay()

        player.participantInformation.isStay() shouldBe true
    }

    "카드를 뽑을수 있다." {
        val player = player()
        val cardDeck = CardDeckTest.cardDeck()

        player.hit(cardDeck)

        player.cardsInHand.cards.size shouldBe 1
    }

    "Stay 선언시 카드를 뽑는 경우 Exception을 던진다." {
        val player = player()
        val cardDeck = CardDeckTest.cardDeck()

        player.stay()

        shouldThrow<IllegalArgumentException> { player.hit(cardDeck) }
    }

    "플레이어의 점수가 21보다 크거나 같을때 카드를 뽑으면 Exception을 던진다." {
        val player = player()
        val cardDeck = CardDeckTest.cardDeck()

        val values = listOf(
            listOf(Card(Ten(), Suit.DIAMOND), Card(Ten(), Suit.DIAMOND), Card(Ten(), Suit.DIAMOND)),
            listOf(Card(Ten(), Suit.DIAMOND), Card(Ten(), Suit.DIAMOND), Card(Ace(), Suit.DIAMOND))
        )

        values.forAll { cards ->
            cards.forEach { player.cardsInHand.add(it) }
            shouldThrow<IllegalArgumentException> { player.hit(cardDeck) }
        }
    }

    "승리시 베팅 금액을 획득한다." {
        val player = Player(ParticipantInformation(Name("dean"), Status.PLAY), ACE_CARD, BetAmount.of(1_000))

        player.score(PlayerScoreStrategy(Dealer(TWO_CARD)))

        player.winningAmount.amount shouldBe Amount(1_000)
    }

    "패배시 베팅 금액을 잃는다." {
        val player = Player(ParticipantInformation(Name("dean"), Status.PLAY), TWO_CARD, BetAmount.of(1_000))

        player.score(PlayerScoreStrategy(Dealer(ACE_CARD)))

        player.winningAmount.amount shouldBe Amount(-1_000)
    }

    "딜러와 점수가 같을 경우 베팅 금액을 잃는다." {
        val player = Player(ParticipantInformation(Name("dean"), Status.PLAY), TWO_CARD, BetAmount.of(1_000))

        player.score(PlayerScoreStrategy(Dealer(TWO_CARD)))

        player.winningAmount.amount shouldBe Amount(-1_000)
    }

    "블랙잭일 경우 베팅 금액의 1.5배를 획득한다." {
        val player = Player(ParticipantInformation(Name("dean"), Status.PLAY), BLACKJACK_CARDS, BetAmount.of(1_000))

        player.score(PlayerScoreStrategy(Dealer(TWO_CARD)))

        player.winningAmount.amount shouldBe Amount(1_500)
    }

    "플레이어와 딜러 모두 블랙잭일 경우 베팅 금액을 획득한다." {
        val player = Player(ParticipantInformation(Name("dean"), Status.PLAY), BLACKJACK_CARDS, BetAmount.of(1_000))

        player.score(PlayerScoreStrategy(Dealer(BLACKJACK_CARDS)))

        player.winningAmount.amount shouldBe Amount(1_000)
    }
}) {
    companion object {
        private fun player() = Player.sit(Name("dean"), BetAmount.of(1_000))
        val BLACKJACK_CARDS = CardsInHand(listOf(Card(Ace(), Suit.HEART), Card(Ten(), Suit.HEART)))
        val TWO_CARD = CardsInHand(listOf(Card(Two(), Suit.HEART)))
        val ACE_CARD = CardsInHand(listOf(Card(Ace(), Suit.HEART)))
    }
}
