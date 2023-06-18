package blackjack.participant

import blackjack.domain.card.Card
import blackjack.domain.card.PlayingCards
import blackjack.domain.deck.Deck
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import blackjack.domain.state.mockState
import blackjack.domain.state.running.Hit
import blackjack.event.DealerEvent
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class DealerTest : StringSpec({

    "딜러를 생성하면 이름은 딜러로 고정되어 있다." {
        val dealer = Dealer(state = mockState)

        dealer.getName() shouldBe "딜러"
    }

    "딜러는 초기 카드가 16이하이면 한 장을 더 뽑는다." {
        val deck = Deck()

        val dealer = Dealer(
            state = Hit(
                playingCards = PlayingCards(
                    cards = mutableSetOf(
                        Card(denomination = Denomination.ACE, suit = Suit.DIAMONDS),
                        Card(denomination = Denomination.TWO, suit = Suit.DIAMONDS),
                    ),
                ),
            ),
        )

        dealer.play(dealerEvent = DealerEvent { }, drawingEvent = { deck.draw() })
        dealer.getCards() shouldNotBe 2
    }

    "딜러는 초기 카드가 17이상이면 한 장을 더 뽑지 않는다." {
        val deck = Deck()

        val dealer = Dealer(
            state = Hit(
                playingCards = PlayingCards(
                    cards = mutableSetOf(
                        Card(denomination = Denomination.ACE, suit = Suit.DIAMONDS),
                        Card(denomination = Denomination.TEN, suit = Suit.DIAMONDS),
                    ),
                ),
            ),
        )

        dealer.play(dealerEvent = DealerEvent { }, drawingEvent = { deck.draw() })
        dealer.getCards() shouldNotBe 2
    }
})
