package blackjack

import baclkjack.domain.card.Card
import baclkjack.domain.card.Cards
import baclkjack.domain.card.Number
import baclkjack.domain.card.Suit
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CardsTest : BehaviorSpec({

    Given(" Cards 를 만든다 0") {
        val cards = Cards()
        When(" 스페이스 6 를 cards 에 넣는다 ") {
            cards.add(Card(Suit.SPADE, Number.SIX))
            Then(" score 는 11 이다") {
                println(" score ${cards.score()}")
                cards.score() shouldBe 6
            }
        }

        When(" 스페이스 K 를 cards 에 넣는다 ") {
            cards.add(Card(Suit.SPADE, Number.KING))
            Then(" score 는 21 이다") {
                cards.score() shouldBe 16
            }
        }
        When(" 스페이스 5 를 cards 에 넣는다 ") {
            cards.add(Card(Suit.SPADE, Number.FIVE))
            Then(" score 는 21 이다") {
                cards.score() shouldBe 21
            }
        }
    }

    Given(" Cards 를 만든다 1") {
        val cards = Cards()
        When(" 스페이스 A 를 cards 에 넣는다 ") {
            cards.add(Card(Suit.SPADE, Number.ACE))
            Then(" score 는 11 이다") {
                println(" score ${cards.score()}")
                cards.score() shouldBe 11
            }
        }

        When(" 스페이스 K 를 cards 에 넣는다 ") {
            cards.add(Card(Suit.SPADE, Number.KING))
            Then(" score 는 21 이다") {
                cards.score() shouldBe 21
            }
        }
    }

    Given(" Cards 를 만든다 2") {
        val cards = Cards()
        When(" 스페이스 A 를 cards 에 넣는다 ") {
            cards.add(Card(Suit.SPADE, Number.ACE))
            Then(" score 는 11 이다") {
                cards.score() shouldBe 11
            }
        }

        When(" 하트 A 를 cards 에 넣는다 ") {
            cards.add(Card(Suit.HEART, Number.ACE))
            Then(" score 는 12 이다") {
                cards.score() shouldBe 12
            }
        }

        When(" 클로버 A 를 cards 에 넣는다 ") {
            cards.add(Card(Suit.CLUB, Number.ACE))
            Then(" score 는 12 이다") {
                cards.score() shouldBe 13
            }
        }

        When(" 다이아 A 를 cards 에 넣는다 ") {
            cards.add(Card(Suit.DIAMOND, Number.ACE))
            Then(" score 는 21 이다") {
                cards.score() shouldBe 14
            }
        }

        When(" 다이아 6 를 cards 에 넣는다 ") {
            cards.add(Card(Suit.DIAMOND, Number.SIX))
            Then(" score 는 21 이다") {
                cards.score() shouldBe 20
            }
        }
    }

    Given(" Cards 를 만든다 3") {
        val cards = Cards()
        When(" 스페이스 A 를 cards 에 넣는다 ") {
            cards.add(Card(Suit.SPADE, Number.ACE))
            Then(" score 는 11 이다") {
                cards.score() shouldBe 11
            }
        }

        When(" 하트 A 를 cards 에 넣는다 ") {
            cards.add(Card(Suit.HEART, Number.ACE))
            Then(" score 는 12 이다") {
                cards.score() shouldBe 12
            }
        }

        When(" 하트 Q 를 cards 에 넣는다 ") {
            cards.add(Card(Suit.HEART, Number.QUEEN))
            Then(" score 는 12 이다") {
                cards.score() shouldBe 12
            }
        }

        When(" 하트 9 를 cards 에 넣는다 ") {
            cards.add(Card(Suit.HEART, Number.NINE))
            Then(" score 는 21 이다") {
                cards.score() shouldBe 21
            }
        }
    }
})
